package com.ardublock.translator.block.akbone;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class akbone_Neopixel_show  extends TranslatorBlock {

	public akbone_Neopixel_show (Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	//@Override
		public String toCode() throws SocketNullException, SubroutineNotDeclaredException
		{
			String Pin ;

			TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
			Pin = translatorBlock.toCode();
			
			
			String ret = "strip_pin"+Pin+".show();\n";
			
			return codePrefix + ret + codeSuffix;
				
		}
}
