package com.ardublock.translator.block.adafruit;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Neopixel_clear  extends TranslatorBlock {

	public Neopixel_clear (Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	//@Override
		public String toCode() throws SocketNullException, SubroutineNotDeclaredException
		{
			String Pin ;

			TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
			Pin = translatorBlock.toCode();
			
			
			String ret = "strip_pin"+Pin+".clear();\n";
			
			return codePrefix + ret + codeSuffix;
				
		}
}
