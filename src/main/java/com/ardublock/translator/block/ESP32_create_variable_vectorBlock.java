package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class ESP32_create_variable_vectorBlock extends TranslatorBlock {
	
	public ESP32_create_variable_vectorBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	//@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		
		String ret = "";
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		String arrayname = tb.toCode().replace("\"","");
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		String arraysize = tb.toCode().replace("\"","");
		tb = this.getRequiredTranslatorBlockAtSocket(2);
		String arraystr = tb.toCode().replace("\"","");
		
		//Deal with line and character positioning
		translator.addDefinitionCommand(" int "+ arrayname +"["+ arraysize +"] = {"+arraystr+"};");
		return ret;
	}
	
}


