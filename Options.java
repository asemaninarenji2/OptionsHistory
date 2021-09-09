public class Options{
	
	private String optionId = null;
	private String stkId=null;
	private String expDate;
	private String optionType;
	private String exercisePrice;
	private double bid;
	private double offer;
	private String dat; 
	
	public Options() {
    }
	public Options(String optionId ,String stkId,String expDate,String optionType, String exercisePrice,double bid,double offer,String dat )
	{
		this.optionId=optionId;
		 this.stkId=stkId;
		this.expDate=expDate;
		this.optionType=optionType;
		this.exercisePrice= exercisePrice;
		this.bid=bid;
		this.offer=offer;
		this.dat=dat; 
	}
	
	public String getOptionId(){
		return optionId;
	}
	public void setOptionId(String optionId){
		this.optionId=optionId;
	}
	public String getStkId(){
		return stkId;
	}
	public void setStkId(String stkId){
		this.stkId=stkId;
	}
	public String getExpDate(){
		return expDate;
	}
	public void setExpDate(String expDate){
		this.expDate=expDate;
	}
	public String getOptionType(){
		return optionType;
	}
	public void setOptionType(String optionType){
		this.optionType=optionType;
	}
	public String getExercisePrice(){
		return exercisePrice;
	}
	public void setExercisePrice(String exercisePrice){
		this.exercisePrice=exercisePrice;
	}
	public double getBid(){
		return bid;
	}
	public void setBid(double bid){
		this.bid=bid;
	}
	public double getOffer(){
		return offer;
	}
	public void setOffer(double offer){
		this.offer=offer;
	}
	public String getDat(){
		return dat;
	}
	public void setDat(String dat){
		this.dat=dat;
	}
	public String toString(){
		return optionId+"   "+stkId+"   "+  expDate+"   "+ optionType+"   "+ exercisePrice+"   "+ bid+"   "+ offer+"   "+ dat;
	} 
	public String brif(){
		return stkId+"   "+exercisePrice+"   "+expDate;
	}
}