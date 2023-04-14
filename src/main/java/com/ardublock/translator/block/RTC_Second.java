package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class RTC_Second extends TranslatorBlock
        {

                public RTC_Second(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
                {
                        super(blockId, translator, codePrefix, codeSuffix, label);
                }

                @Override
                public String toCode() throws SocketNullException {
                        return codePrefix + "RTC_PCF8563_Second" + codeSuffix;
                }

        }

