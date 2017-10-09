package cn.com.ecict.local;

/**
 * Created by root on 17-9-30.
 */
public class Result {
    private int exitCode;
    private String output;


    public Result(int exitCode, String output) {
        this.exitCode = exitCode;
        this.output = output;

    }

    public int getExitCode() {
        return exitCode;
    }

    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

}
