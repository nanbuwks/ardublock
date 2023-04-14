package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Cleaner_left_sensor extends TranslatorBlock
	{

		public Cleaner_left_sensor(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
		{
			super(blockId, translator, codePrefix, codeSuffix, label);
		}

		@Override
		public String toCode() throws SocketNullException {
                        translator.addSetupCommand(" pinMode(PD4,INPUT_PULLUP);");
			return codePrefix + "PE11" + codeSuffix;
		}
		
	}
