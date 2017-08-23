package com.cristoffer.application.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
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

	private HorizontalPanel addPanel = new HorizontalPanel();
	private TextBox operand1TextBox = new TextBox();
	private TextBox operand2TextBox = new TextBox();
	private TextBox operatorTextBox = new TextBox();
	private FlexTable answerTable = new FlexTable();
	private FlexTable calculatorTable = new FlexTable();
	private String[][] calculatorButtons = { { "C", ".", "%", "/" }, { "7", "8", "9", "*" }, { "4", "5", "6", "-" },
			{ "1", "2", "3", "+" }, { "0", "=" } };
	// private HashMap<CalculatorPanels, HorizontalPanel> panelMap = new
	// HashMap<>();

	/**
	 * Entry point method.
	 */
	public void onModuleLoad() {
		createButtons();
		createAnswerTable();
		addPanel.add(operand1TextBox);
		addPanel.add(operatorTextBox);
		addPanel.add(operand2TextBox);

		// TODO Assemble Main panel.
		mainPanel.add(answerTable);
		mainPanel.add(addPanel);
		mainPanel.add(calculatorTable);
		// TODO Associate the Main panel with the HTML host page.
		RootPanel.get("calc").add(mainPanel);
		// TODO Move cursor focus to the input box.
		operand1TextBox.setReadOnly(true);
		operand2TextBox.setReadOnly(true);
		operatorTextBox.setReadOnly(true);
		operand1TextBox.setFocus(true);
		operatorTextBox.setWidth("20px");
		operand1TextBox.addKeyDownHandler(handleKeyDown());
		operand2TextBox.addKeyDownHandler(handleKeyDown());
	}

	private void calculate() {

		final String operator = operatorTextBox.getText().trim();
		Calculations calc = new Calculations();
		double answer;
		if (!isDouble(operand1TextBox.getText().trim()) || !isDouble(operand2TextBox.getText().trim())) {
			Window.alert("You have entered a non valid binary operator or one of the operands is not an integer or a "
					+ "double");
			return;
		}
		double operand1 = Double.parseDouble(operand1TextBox.getText());
		double operand2 = Double.parseDouble(operand2TextBox.getText());
		answer = calc.Calculate(operand1, operand2, operator);
		updateAnswerTable(operand1, operator, operand2, answer);

	}

	private void updateAnswerTable(Double operandOne, String operator, Double operandTwo, Double answer) {
		int row = answerTable.getRowCount();
		if (row < 10) {
			answerTable.setText(row, 0,
					Double.toString(operandOne) + " " + operator + " " + Double.toString(operandTwo));
			answerTable.setText(row, 1, Double.toString(answer));
		} else {
			answerTable.setText(row, 0,
					Double.toString(operandOne) + " " + operator + " " + Double.toString(operandTwo));
			answerTable.setText(row, 1, " = ");
			answerTable.setText(row, 2, Double.toString(answer));
			answerTable.removeRow(1);
		}
	}

	private void createButtons() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				if (i == 4) {
					calculatorTable.setWidget(i, 0, new Button(calculatorButtons[i][0], handleClick()));
					calculatorTable.setWidget(i, 1, new Button(calculatorButtons[i][1], handleClick()));
					calculatorTable.getWidget(i, 0).setSize("100%", "80px");
					calculatorTable.getWidget(i, 1).setSize("100%", "80px");
					calculatorTable.getWidget(i, 0).setStyleName("calcButton");
					calculatorTable.getWidget(i, 1).setStyleName("calcButton");
					break;
				} else {
					calculatorTable.setWidget(i, j, new Button(calculatorButtons[i][j], handleClick()));
					calculatorTable.getWidget(i, j).setSize("80px", "80px");
					calculatorTable.getWidget(i, j).setStyleName("calcButton");
				}
			}
		}
		// Sets the zero and equals buttons to span over 2 columns each
		calculatorTable.getFlexCellFormatter().setColSpan(4, 0, 2);
		calculatorTable.getFlexCellFormatter().setColSpan(4, 1, 3);
	}

	private void createAnswerTable() {
		answerTable.setText(0, 0, "Equation");
		answerTable.setText(0, 1, " ");
		answerTable.setText(0, 2, "Answer");

		for (int i = 1; i < 10; i++)
			answerTable.setText(i, 0, "-");
	}

	private boolean isOperator(String operator) {
		if (operator.equals("*") || operator.equals("+") || operator.equals("-") || operator.equals("/")
				|| operator.equals("%")) {
			return true;
		} else {
			return false;
		}
	}

	private void clearCalc() {
		operand2TextBox.setText("");
		operand1TextBox.setText("");
		operatorTextBox.setText("");
		operand1TextBox.setFocus(true);
	}

	private void onButtonPress(String buttonText) {
		if (buttonText.equals("C")) {
			clearCalc();
		} else if (buttonText.equals("=")) {
			calculate();
			clearCalc();
		} else if (isOperator(buttonText) && !isOperator(operatorTextBox.getText())) {
			operatorTextBox.setText(buttonText);
			operand2TextBox.setFocus(true);
		} else if (!isOperator(buttonText) && !isOperator(operatorTextBox.getText())) {
			operand1TextBox.setText(operand1TextBox.getText() + buttonText);
			operand1TextBox.setFocus(true);
		} else if (!isOperator(buttonText) && isOperator(operatorTextBox.getText())) {
			operand2TextBox.setText(operand2TextBox.getText() + buttonText);
			operand2TextBox.setFocus(true);
		}
	}

	private KeyDownHandler handleKeyDown() {
		return new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {
				NumpadKeyConvert numpadKey = new NumpadKeyConvert();
				onButtonPress(numpadKey.convertKey(event));
			}
		};
	}

	private ClickHandler handleClick() {
		return new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Button btn = (Button) event.getSource();
				onButtonPress(btn.getText());
			}
		};
	}

	// Checks if a String could be seen as a double
	public boolean isDouble(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}