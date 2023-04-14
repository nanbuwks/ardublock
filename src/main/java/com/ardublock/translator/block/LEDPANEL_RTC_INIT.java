package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class LEDPANEL_RTC_INIT extends TranslatorBlock {

        public LEDPANEL_RTC_INIT(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
        {
                super(blockId, translator, codePrefix, codeSuffix, label);
        }

        // @Override
        public String toCode() throws SocketNullException, SubroutineNotDeclaredException
        {

                String ret = "";
                String Year;
		String Month;
		String Day;
		String Hour;
		String Minute;
		String Second;
		TranslatorBlock translatorBlock;
		translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
		Year=translatorBlock.toCode();
		translatorBlock=this.getRequiredTranslatorBlockAtSocket(1);
		Month=translatorBlock.toCode();
		translatorBlock=this.getRequiredTranslatorBlockAtSocket(2);
		Day=translatorBlock.toCode();
		translatorBlock=this.getRequiredTranslatorBlockAtSocket(3);
		Hour=translatorBlock.toCode();
		translatorBlock=this.getRequiredTranslatorBlockAtSocket(4);
		Minute=translatorBlock.toCode();
		translatorBlock=this.getRequiredTranslatorBlockAtSocket(5);
		Second=translatorBlock.toCode();
                //Deal with line and character positioning
                translator.addHeaderFile("Wire.h");
                translator.addHeaderFile("RTClib.h");
		translator.addDefinitionCommand("RTC_PCF8563 RTC;\n");
                translator.addSetupCommand("Wire.begin(22,21);");
                translator.addSetupCommand("RTC.begin();");
                translator.addSetupCommand("RTC.adjust(DateTime("+Year+","+Month+","+Day+","+Hour+","+Minute+","+Second+"));");
		return ret;

        }

}
