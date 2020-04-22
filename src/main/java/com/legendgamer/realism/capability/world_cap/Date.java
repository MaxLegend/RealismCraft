package com.legendgamer.realism.capability.world_cap;

public final class Date implements IDate
{
	private boolean isSnow;
    private int year;
    private byte day, month;

    @Override
    public void addDay(byte day) 
    {
        if (this.day <= 30)
            this.day += day;
    }

    @Override
    public void addMonth(byte month)
    {
        if (this.month <= 12)
            this.month += month;
    }

    @Override
    public void remMonth(byte month)
    {
        if (this.month != 0)
            this.month -= month;
    }
    @Override
    public void setMonth(byte month)
    {
        this.month = month;
    }
    @Override
    public byte getMonth()
    {
        return month;
    }
    
    @Override
    public void addYear(int year)
    {
        this.year += year;
    }

    @Override
    public void remDay(byte day)
    {
        if (this.day != 0)
            this.day -= day;
    }


    @Override
    public void setDay(byte day)
    {
        this.day = day;
    }

    @Override
    public void setYear(int year)
    {
        this.year = year;
    }

    @Override
    public byte getDay()
    {
        return day;
    }



    @Override
    public int getYear()
    {
        return year;
    }

	@Override
	public boolean setEnableSnow(boolean isSnow) {
		
		return this.isSnow = isSnow;
	}

	@Override
	public boolean getEnableSnow() {

		return isSnow;
	}
}