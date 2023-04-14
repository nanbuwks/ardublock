package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class ESP32_SPIFF_WRITEBlock extends TranslatorBlock {
	
	public ESP32_SPIFF_WRITEBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	//@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		
		String ret = "";
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		String path = tb.toCode();
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		String value = tb.toCode();
		
		//Deal with line and character positioning
		translator.addHeaderFile("FS.h");
		translator.addHeaderFile("SPIFFS.h");
		translator.addDefinitionCommand("FILE writefile;");
		translator.addSetupCommand(" if(!SPIFFS.begin()){");
		translator.addSetupCommand("   Serial.println(\"SPIFFS Mount Failed\");");
		translator.addSetupCommand("   return;");
		translator.addSetupCommand(" }");
		translator.addSetupCommand(" ");
                translator.addSetupCommand( "    readfile = SPIFFS.open("+path+ ");" );
                translator.addSetupCommand( "       if(!readfile || readfile.isDirectory()){" );
                translator.addSetupCommand( "        Serial.println(\"Failed to open file for reading\");" );
translator.addSetupCommand( "    }");
translator.addSetupCommand( "    if(writefile.print(message)){");
translator.addSetupCommand( "        Serial.println(\"File written\");");
translator.addSetupCommand( "    } else {");
translator.addSetupCommand( "        Serial.println(\"Write failed\");");
translator.addSetupCommand( "    }");
translator.addSetupCommand( "");
translator.addSetupCommand( "    writefile.close();");



		return ret;
	}
	
}


