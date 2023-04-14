package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class LEDPANEL_FILL extends TranslatorBlock
        {

                public LEDPANEL_FILL(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
                {
                        super(blockId, translator, codePrefix, codeSuffix, label);
                }

                @Override
                public String toCode() throws SocketNullException, SubroutineNotDeclaredException
                {

                TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
                String x1 = tb.toCode();
                tb = this.getRequiredTranslatorBlockAtSocket(1);
                String y1 = tb.toCode();
                tb = this.getRequiredTranslatorBlockAtSocket(2);
                String x2 = tb.toCode();
                tb = this.getRequiredTranslatorBlockAtSocket(3);
                String y2 = tb.toCode();
                tb = this.getRequiredTranslatorBlockAtSocket(4);
                String red = tb.toCode();
                tb = this.getRequiredTranslatorBlockAtSocket(5);
                String green = tb.toCode();
                tb = this.getRequiredTranslatorBlockAtSocket(6);
                String blue = tb.toCode();

                String ret = 
	"display.fillRect("+x1+","+y1+","+x2+","+y2+",display.color888("+red+","+green+","+blue+"));\n";

		return ret;
	        }	

        }


