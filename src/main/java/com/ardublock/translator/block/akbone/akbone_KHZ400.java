package com.ardublock.translator.block.akbone;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class akbone_KHZ400 extends TranslatorBlock
{

	public akbone_KHZ400(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException {
		return codePrefix + "NEO_KHZ400" + codeSuffix;
	}

}
