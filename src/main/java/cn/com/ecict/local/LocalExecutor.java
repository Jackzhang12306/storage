package cn.com.ecict.local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 通过SynchronousQueue队列保证只有一个线程在获取外部进程的退出码，由线程池提供超时功能。
 * Created by root on 17-9-30.
 */
public class LocalExecutor {
    static final Logger logger = LoggerFactory.getLogger(LocalExecutor.class);
    static ExecutorService pool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 3L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

    public static final Result execute(String command, long timeout) {
        Process process = null;
        InputStream pIn = null;
        InputStream pErr = null;
        StreamGobbler outputGobbler = null;
        StreamGobbler errorGobbler = null;
        Future<Integer> executeFuture = null;
        try {
            logger.info(command.toString());
            process = Runtime.getRuntime().exec(command);
            final Process p = process;
            // close process's output stream.
            p.getOutputStream().close();
            pIn = process.getInputStream();
            outputGobbler = new StreamGobbler(pIn, "OUTPUT");
            outputGobbler.start();
            pErr = process.getErrorStream();
            errorGobbler = new StreamGobbler(pErr, "ERROR");
            errorGobbler.start();
            // lambda表达式
            executeFuture = pool.submit(()->{
                p.waitFor();
                return p.exitValue();
            });
            //等待执行结果，最长等待timeout秒(在第timeout秒取结果)，timeout秒后中止任务
            int exitCode = executeFuture.get(timeout, TimeUnit.MILLISECONDS);
            if(exitCode==0){
                return new Result(exitCode, outputGobbler.getContent());
            }else{
                return new Result(exitCode, errorGobbler.getContent());
            }

        } catch (IOException ex) {
            String errorMessage = "The command [" + command + "] execute failed.";
            logger.error(errorMessage, ex);
            return new Result(-1, null);
        } catch (TimeoutException ex) {
            String errorMessage = "The command [" + command + "] timed out.";
            logger.error(errorMessage, ex);
            return new Result(-1, null);
        } catch (ExecutionException ex) {
            String errorMessage = "The command [" + command + "] did not complete due to an execution error.";
            logger.error(errorMessage, ex);
            return new Result(-1, null);
        } catch (InterruptedException ex) {
            String errorMessage = "The command [" + command + "] did not complete due to an interrupted error.";
            logger.error(errorMessage, ex);
            return new Result(-1, null);
        } finally {
            if (executeFuture != null) {
                try {
                    executeFuture.cancel(true);
                } catch (Exception ignore) {
                    ignore.printStackTrace();
                }
            }
            if (pIn != null) {
                closeQuietly(pIn);
                if (outputGobbler != null && !outputGobbler.isInterrupted()) {
                    outputGobbler.interrupt();
                }
            }
            if (pErr != null) {
                closeQuietly(pErr);
                if (errorGobbler != null && !errorGobbler.isInterrupted()) {
                    errorGobbler.interrupt();
                }
            }
            if (process != null) {
                process.destroy();
            }
        }
    }

    private static void closeQuietly(Closeable c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (IOException e) {
            logger.error("exception", e);
        }
    }

    public static final Result exec(String cmd) {
        Result result;
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(cmd);
            BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stderrReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            StringBuffer output = new StringBuffer();
            while ((line = stdoutReader.readLine()) != null) {
                output.append(line).append("\n");
            }

            StringBuffer error= new StringBuffer();
            while ((line = stderrReader.readLine()) != null) {
                error.append(line).append("\n");
            }
            int exitVal = process.waitFor();
            if(exitVal==0){
                result=new Result(exitVal,output.toString());
            }else{
                result=new Result(exitVal,error.toString());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){
        System.out.println("###1.execute(java) 1000ms");
        Result result=LocalExecutor.execute("java",1000);
        System.out.println(result.getExitCode());
        System.out.println(result.getOutput());
        System.out.println("###2.execute(>)");
        //result=LocalExecutor.execute("java -version > /root/a.txt",1000);
        result=LocalExecutor.execute("sh /root/data/genData.sh > /root/a.txt",1000);
        System.out.println(result.getExitCode());
        System.out.println(result.getOutput());
    /*    System.out.println("###3.exec(java)");
        result=LocalExecutor.exec("java");
        System.out.println(result.getExitCode());
        System.out.println(result.getOutput());*/

    }
}
