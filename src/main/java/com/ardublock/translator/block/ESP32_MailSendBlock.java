package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class ESP32_MailSendBlock extends TranslatorBlock {
	
	public ESP32_MailSendBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	//@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		
		String ret = "";
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		String title = tb.toCode();
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		String to = tb.toCode();
		tb = this.getRequiredTranslatorBlockAtSocket(2);
		String body = tb.toCode();
		
		//Deal with line and character positioning
		ret=ret
+      "  Serial.print(\"mail sending...\");"
+      "  mail.send("+to+","+title+","+body+");"
+      "  Serial.println(\"done.\");"
+      "  delay(1000*60*SmtpSendInterval);";
		return ret;
	}
	
}


