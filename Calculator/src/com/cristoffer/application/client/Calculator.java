package com.cristoffer.application.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class Calculator implements EntryPoint {
	private VerticalPanel mainPanel = new VerticalPanel();
	private VerticalPanel calculatorPanel = new VerticalPanel();
	private HorizontalPanel addPanel = new HorizontalPanel();
	private HorizontalPanel equationPanel, buttonRowOne, buttonRowTwo, buttonRowThree, buttonRowFour, buttonRowFive;
	private TextBox operand1TextBox = new TextBox();
	private TextBox operand2TextBox = new TextBox();
	private TextBox equationBox = new TextBox();
	private Button calculateButton = new Button("Calculate");
	private MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	private SuggestBox operatorTextBox = new SuggestBox(oracle);
	private FlexTable answerTable = new FlexTable();
	private FlexTable calculatorTable = new FlexTable();
	private String[][] calculatorButtons = {{"C", ",", "%", "/"}, {"7", "8", "9", "*"},{"4", "5", "6", "-"},
			{"1", "2", "3", "+"}, {"0", "="}};
//	private HashMap<CalculatorPanels, HorizontalPanel> panelMap = new HashMap<>();

	/**
	 * Entry point method.
	 */
	public void onModuleLoad() {
		
		equationPanel = new HorizontalPanel();
//		buttonRowOne = new HorizontalPanel();
//		buttonRowTwo = new HorizontalPanel();
//		buttonRowThree = new HorizontalPanel();
//		buttonRowFour = new HorizontalPanel();
//		buttonRowFive = new HorizontalPanel();

		answerTable.setText(0, 0, "Question");
		answerTable.setText(0, 1, " ");
		answerTable.setText(0, 2, "Answer");
		
		for (int i = 1; i < 10; i++)
			answerTable.setText(i, 0, Integer.toString(i));
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				if(i == 4) {
					calculatorTable.setWidget(i, 0, new Button(calculatorButtons[i][0], handleClick()));
					calculatorTable.setWidget(i, 1, new Button(calculatorButtons[i][1], handleClick()));
					calculatorTable.getWidget(i, 0).setSize("100%", "80px");
					calculatorTable.getWidget(i, 1).setSize("100%", "80px");
					break;
				}
				else {
					calculatorTable.setWidget(i, j, new Button(calculatorButtons[i][j], handleClick()));
					calculatorTable.getWidget(i, j).setSize("80px", "80px");
				}
			}
		}
		//Sets the zero and equals buttons to span over 2 columns each
		calculatorTable.getFlexCellFormatter().setColSpan(4, 0, 2);
		calculatorTable.getFlexCellFormatter().setColSpan(4, 1, 3);

		addPanel.add(operand1TextBox);
		addPanel.add(operatorTextBox);
		addPanel.add(operand2TextBox);
		addPanel.add(calculateButton);
		
		addPanel.add(calculatorTable);


		// TODO Assemble Main panel.
		mainPanel.add(answerTable);
		mainPanel.add(addPanel);
		// TODO Associate the Main panel with the HTML host page.
		RootPanel.get("calc").add(mainPanel);
		// TODO Move cursor focus to the input box.
		calculateButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				calculate();
			}
		});
	}

	private void calculate() {

		final String operator = operatorTextBox.getText().trim();
		calculateButton.setFocus(true);
		if ((!isValidOperator(operator)) 
				|| !isDouble(operand1TextBox.getText().trim()) || !isDouble(operand2TextBox.getText().trim())) {
			Window.alert("You have entered a non valid binary operator or one of the operands is not an integer or a "
					+ "double");
			return;
		}

		double operand1 = Double.parseDouble(operand1TextBox.getText());
		double operand2 = Double.parseDouble(operand2TextBox.getText());
		double answer;
		int row = answerTable.getRowCount();

		// Multiplication
		if (operator.equals("*")) {
			answer = multiplication(operand1, operand2);
//			Window.alert("The answer is: " + answer);
		}

		// Modulo
		else if (operator.equals("%")) {
			answer = operand1 % operand2;
//			Window.alert("The answer is: " + answer);
		}

		// addition
		else if (operator.equals("/")) {
			answer = division(operand1, operand2);
//			Window.alert("The answer is: " + answer);
		}
		
		else {
			answer = addition(operand1, operand2);
		}
		
		if (row < 10) {
		answerTable.setText(row, 0, Double.toString(operand1) + " " + operator + " " + Double.toString(operand2));
		answerTable.setText(row, 1, Double.toString(answer));
		}
		else {
			answerTable.setText(row, 0, Double.toString(operand1) + " " + operator + " " + Double.toString(operand2));
			answerTable.setText(row, 1, " = ");
			answerTable.setText(row, 2, Double.toString(answer));
			answerTable.removeRow(1);
		}

	}

	private boolean isValidOperator(String operator) {
		if (operator.equals("*") || operator.equals("+") || operator.equals("-") ||
				operator.equals("/") || operator.equals("%")) {
			return true;
		}
		else {
			return false;
		}
	}
	private double addition(double operandOne, double operandTwo) {
		return operandOne + operandTwo;
	}

	private double subtraction(double operandOne, double operandTwo) {
		return operandOne - operandTwo;
	}

	private double multiplication(double operandOne, double operandTwo) {
		return operandOne * operandTwo;
	}

	private double division(double operandOne, double operandTwo) {
		return operandOne / operandTwo;
	}

	private double modulus(double operandOne, double operandTwo) {
		return operandOne % operandTwo;
	}

	private ClickHandler handleClick() {
		return new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

		}
	};
}
	
	// Checks if a String could be seen as an integer
	public boolean isDouble(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}