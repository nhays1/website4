package website4.model;

public class numbers {
	
	private Double first,second,third,result;
	
	public numbers() {
		
	}

	public numbers(Double first ,Double second,Double third) {
		this.first=first;
		this.second=second;
		this.third=third;
	}
	public void setnubms(Double first ,Double second,Double third) {
		this.first=first;
		this.second=second;
		this.third=third;
	}
	
	public Double add() {
		
		 result=first+second+third;
		 return result;
	}
	
	public Double multi() {
		result=first*second;
		return result;
	}
	
	
	
	
	
	public Double getresult() {
		return result;
	}
	
	public Double getfirst() {
		return first;
	}
	public Double getsecond() {
		return second;
	}
	public Double getthird() {
		return third;
	}


	public void setfirst(Double first) {
		this.first=first;
		
	}
	public void setthird(Double third) {
		this.third=third;
		
	}
	public void setsecond(Double second) {
		this.second=second;
		
	}
	

}
