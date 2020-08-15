package tech.hoppr.duple;

public class DayWeather {
    private double mMinTemp;
    private double mMaxTemp;
    private String mDescription;

    public DayWeather(double minTemp, double maxTemp, String description) {
        this.mMinTemp = minTemp;
        this.mMaxTemp = maxTemp;
        this.mDescription = description;
    }

    public double getmMinTemp() {
        return mMinTemp;
    }

    public void setmMinTemp(double mMinTemp) {
        this.mMinTemp = mMinTemp;
    }

    public double getmMaxTemp() {
        return mMaxTemp;
    }

    public void setmMaxTemp(double mMaxTemp) {
        this.mMaxTemp = mMaxTemp;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
