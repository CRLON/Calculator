package com.cristoffer.application.client;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;

public class NumpadKeyConvert {
	public String convertKey(KeyDownEvent event) {
		
		String keyPressedString = "";
		
		switch (event.getNativeKeyCode()) {
		case KeyCodes.KEY_NUM_ZERO: keyPressedString = "0";
		break;
		case KeyCodes.KEY_NUM_ONE: keyPressedString = "1";
		break;
		case KeyCodes.KEY_NUM_TWO: keyPressedString = "2";
		break;
		case KeyCodes.KEY_NUM_THREE: keyPressedString = "3";
		break;
		case KeyCodes.KEY_NUM_FOUR: keyPressedString = "4";
		break;
		case KeyCodes.KEY_NUM_FIVE: keyPressedString = "5";
		break;
		case KeyCodes.KEY_NUM_SIX: keyPressedString = "6";
		break;
		case KeyCodes.KEY_NUM_SEVEN: keyPressedString = "7";
		break;
		case KeyCodes.KEY_NUM_EIGHT: keyPressedString = "8";
		break;
		case KeyCodes.KEY_NUM_NINE: keyPressedString = "9";
		break;
		case KeyCodes.KEY_NUM_DIVISION: keyPressedString = "/";
		break;
		case KeyCodes.KEY_NUM_MULTIPLY: keyPressedString = "*";
		break;
		case KeyCodes.KEY_NUM_MINUS: keyPressedString = "-";
		break;
		case KeyCodes.KEY_NUM_PLUS: keyPressedString = "+";
		break;
		case KeyCodes.KEY_ENTER: keyPressedString = "=";
		break;
		case KeyCodes.KEY_NUM_PERIOD: keyPressedString = ".";
		break;
		}
		return keyPressedString;
	}

}
