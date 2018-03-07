package website4.controller;


import website4.model.numbers;

public class NumbersController {
	private numbers model;
	
	public void setModel(numbers model) {
		
		
		this.model = model;
	}
	
	
	public Double add(Double first, Double second, Double third) {
		model.setnubms(first, second, third);
		return model.add();
	}
	public Double multiply(Double first, Double second) {
		model.setnubms(first, second, 1.0);
		return model.multi();
	}

}
