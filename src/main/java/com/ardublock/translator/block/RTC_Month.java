package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class RTC_Month extends TranslatorBlock
        {

                public RTC_Month(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
                {
                        super(blockId, translator, codePrefix, codeSuffix, label);
                }

                @Override
                public String toCode() throws SocketNullException {
                        return codePrefix + "RTC_PCF8563_Month" + codeSuffix;
                }

        }
