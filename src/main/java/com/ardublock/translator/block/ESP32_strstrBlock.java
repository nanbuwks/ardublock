package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class ESP32_strstrBlock extends TranslatorBlock
{
	public ESP32_strstrBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(1);
		String ret = " (strstr(";
		ret = ret + tb.toCode();
		ret = ret + " ,";
		tb = this.getRequiredTranslatorBlockAtSocket(0);
		ret = ret + tb.toCode();
		ret = ret + ") > 0 )";
		return codePrefix + ret + codeSuffix;
	}
}
