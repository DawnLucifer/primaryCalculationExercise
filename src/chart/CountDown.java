package chart;

public class CountDown {
    private int minutes;
    private int seconds;
    private int time;

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    private int enable;

    public CountDown(int minutes, int seconds, int enable) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.enable = enable;
    }

    public CountDown(CountDown countDownTime) {
        this.minutes = countDownTime.minutes;
        this.time = countDownTime.time;
        this.seconds = countDownTime.seconds;
        this.enable = countDownTime.enable;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
        minutes = time / 60;
        seconds = time % 60;
    }

    private void updateTime() {
        this.time = minutes * 60 + seconds;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
        updateTime();
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
        updateTime();
    }

    public int getMinutes() {
        return minutes;
    }


    public int getSeconds() {
        return seconds;
    }

    public void run() {
        if (time > 0) {
            time--;
            setTime(time);
        }
    }

    @Override
    public String toString() {
        return minutes + ":" + seconds;
    }
}
