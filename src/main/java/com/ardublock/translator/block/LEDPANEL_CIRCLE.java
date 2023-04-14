package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class LEDPANEL_CIRCLE extends TranslatorBlock
        {

                public LEDPANEL_CIRCLE(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
                {
                        super(blockId, translator, codePrefix, codeSuffix, label);
                }

                @Override
                public String toCode() throws SocketNullException, SubroutineNotDeclaredException
                {

                TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
                String x = tb.toCode();
                tb = this.getRequiredTranslatorBlockAtSocket(1);
                String y = tb.toCode();
                tb = this.getRequiredTranslatorBlockAtSocket(2);
                String r = tb.toCode();
                tb = this.getRequiredTranslatorBlockAtSocket(3);
                String red = tb.toCode();
                tb = this.getRequiredTranslatorBlockAtSocket(4);
                String green = tb.toCode();
                tb = this.getRequiredTranslatorBlockAtSocket(5);
                String blue = tb.toCode();

                String ret = 
	"display.fillCircle("+x+","+y+","+r+",display.color888("+red+","+green+","+blue+"));\n";

		return ret;
	        }	

        }


