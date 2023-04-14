package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class ESP32_WiFiBlock extends TranslatorBlock {
	
	public ESP32_WiFiBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	//@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		
		String ret = "";
		ret = "";
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		String ssid = tb.toCode();
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		String passphase = tb.toCode();
		
		//Deal with line and character positioning
		translator.addHeaderFile("WiFi.h");
		translator.addSetupCommand(" WiFi.begin("+ssid+","+passphase+");");
		translator.addSetupCommand("");
		translator.addSetupCommand(" while (WiFi.status() != WL_CONNECTED) {");
		translator.addSetupCommand(" delay(500);");
		translator.addSetupCommand(" Serial.print(\".\");");
		translator.addSetupCommand(" }");
		translator.addSetupCommand(" Serial.println(\"\");");
		translator.addSetupCommand(" Serial.println(\"WiFi connected\");");
		translator.addSetupCommand(" Serial.println(\"IP address: \");");
		translator.addSetupCommand(" Serial.println(WiFi.localIP());");
		return ret;
	}
	
}


