package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class LEDPANEL_PRINT extends TranslatorBlock
        {

                public LEDPANEL_PRINT(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
                {
                        super(blockId, translator, codePrefix, codeSuffix, label);
                }

                @Override
                public String toCode() throws SocketNullException, SubroutineNotDeclaredException
                {

                TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
                String content = tb.toCode();
                tb = this.getRequiredTranslatorBlockAtSocket(1);
                String x = tb.toCode();
                tb = this.getRequiredTranslatorBlockAtSocket(2);
                String y = tb.toCode();

                String ret = 
	"display.setCursor("+x+","+y+");\n"
+	"display.setFont(&fonts::Font0);\n"
+	"display.setTextColor(TFT_WHITE, TFT_BLACK);\n"
+	"display.setTextSize(1);\n"
+	"display.print("+content+");\n";

		return ret;
	        }	

        }


