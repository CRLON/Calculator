package com.cristoffer.application.client;

public class Calculations {

	public double Calculate(Double operandOne, Double operandTwo, String operator) {
		double answer = 0.0;
		switch (operator) {
		case "+":
			answer = operandOne + operandTwo;
			break;
		case "-":
			answer = operandOne - operandTwo;
			break;
		case "*":
			answer = operandOne * operandTwo;
			break;
		case "/":
			answer = operandOne / operandTwo;
			break;
		case "%":
			answer = operandOne % operandTwo;
			break;
		}
		return answer;
	}
}
