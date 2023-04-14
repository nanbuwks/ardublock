package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class LEDPANEL_RTC extends TranslatorBlock {

        public LEDPANEL_RTC(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
        {
                super(blockId, translator, codePrefix, codeSuffix, label);
        }

        // @Override
        public String toCode() throws SocketNullException, SubroutineNotDeclaredException
        {

                String ret = "";
                //Deal with line and character positioning
                translator.addHeaderFile("Wire.h");
                translator.addHeaderFile("RTClib.h");
		translator.addDefinitionCommand("RTC_PCF8563 RTC;\n");
		translator.addDefinitionCommand("static int RTC_PCF8563_Year;");
		translator.addDefinitionCommand("static int RTC_PCF8563_Month;");
		translator.addDefinitionCommand("static int RTC_PCF8563_Day;");
		translator.addDefinitionCommand("static int RTC_PCF8563_Hour;");
		translator.addDefinitionCommand("static int RTC_PCF8563_Minute;");
		translator.addDefinitionCommand("static int RTC_PCF8563_Second;");
                translator.addSetupCommand("Wire.begin(22,21);");
                translator.addSetupCommand("RTC.begin();");
		ret=  
	"	DateTime RTC_PCF8563_now = RTC.now();\n"
+	"	RTC_PCF8563_Year=RTC_PCF8563_now.year();\n"
+	"	RTC_PCF8563_Month=RTC_PCF8563_now.month();\n"
+	"	RTC_PCF8563_Day=RTC_PCF8563_now.day();\n"
+	"	RTC_PCF8563_Hour=RTC_PCF8563_now.hour();\n"
+	"	RTC_PCF8563_Minute=RTC_PCF8563_now.minute();\n"
+	"	RTC_PCF8563_Second=RTC_PCF8563_now.second();\n";
		return ret;

        }

}
