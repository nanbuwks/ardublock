package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class ESP32_SPIFF_INITBlock extends TranslatorBlock {
	
	public ESP32_SPIFF_INITBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	//@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		
		String ret = "";
		
		//Deal with line and character positioning
		translator.addHeaderFile("FS.h");
		translator.addHeaderFile("SPIFF.h");
		translator.addSetupCommand(" if(!SPIFFS.begin(true)){");
		translator.addSetupCommand("   Serial.println(\"SPIFF Mount Failed\");");
		translator.addSetupCommand("   return");
		translator.addSetupCommand(" }");
		return ret;
	}
	
}


