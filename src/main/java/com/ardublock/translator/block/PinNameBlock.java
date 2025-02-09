package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class PinNameBlock extends TranslatorBlock
{
	public PinNameBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode()
	{
		return label;
	}

}
