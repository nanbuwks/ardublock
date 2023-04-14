package com.ardublock.translator.block.akbone;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class akbone_NEO_RGB extends TranslatorBlock
{

	public akbone_NEO_RGB(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException {
		return codePrefix + "NEO_RGB" + codeSuffix;
	}

}
