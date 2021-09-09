public class Prices
{
	private String stkId;
	private String dat;
	private double price;
	
	public Prices(String stkId,String dat,double price)
	{
		this.stkId=stkId;
		this.dat=dat;
		this.price=price;
	}
	
	public void setPrice(double price)
	{
		this.price=price;
	}
	
	public void setDat(String dat)
	{
		this.dat=dat;
	}
	public void setStkId(String stkId)
	{
		this.stkId=stkId;
	}
	public String getStkId()
	{
		return stkId;
	}
	public String getDat()
	{
		return dat;
	}
	public double getPrice()
	{
		return price;
	}
	
	//@override
	public String toString()
	{
		return stkId+"\t"+ price+"\t"+dat;
	}
}