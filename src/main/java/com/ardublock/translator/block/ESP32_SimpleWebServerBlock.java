package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class ESP32_SimpleWebServerBlock extends TranslatorBlock
{
	public ESP32_SimpleWebServerBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);

		String port = translatorBlock.toCode();
                translator.addDefinitionCommand(    "WiFiServer server("+port+");");
                translator.addDefinitionCommand(    "char request[80];");
                translator.addDefinitionCommand(    "char Get[80];");
                translator.addDefinitionCommand(    "int charcount=0;");
                translator.addSetupCommand(" server.begin();");
		String ret = ""

+ " WiFiClient client = server.available();\n"
+ "\n"
+ "if (client) {\n"
+ " Serial.println(\"new client\");\n"

+ "    memset(request,0,sizeof(request));\n"
+ "    charcount=0;\n"
+ "    boolean currentLineIsBlank = true;\n"
+ "    \n"
+ "    while (client.connected()) {\n"
+ "      if (client.available()) {\n"
+ "        char c = client.read();\n"
+ "        Serial.write(c);\n"
+ "        request[charcount]=c;\n"
+ "        if (charcount<sizeof(request)-1) charcount++;\n"
+ "        if (c == '\\n' && currentLineIsBlank) {\n"
+ "            client.println(\"HTTP/1.1 200 OK\");\n"
+ "            client.println(\"Content-type:text/html\");\n"
+ "            client.println();\n"
+ " \n";

		translatorBlock = getTranslatorBlockAtSocket(1);
		while (translatorBlock != null)
		{
			ret = ret + translatorBlock.toCode();
			translatorBlock = translatorBlock.nextTranslatorBlock();
		}
                ret=ret
+ " \n"
+ "            client.println();\n"
+ "            break;\n"
+ "          }\n"
+ "          if (c == '\\n') {\n"
+ "            if ( 0 <  (strstr(request ,\"GET /\") ))\n"
+ "            {\n"
+ "              strcpy(Get,request);\n"
+ "            }\n"
+ "            currentLineIsBlank = true;\n"
+ "            memset(request,0,sizeof(request));\n"
+ "            charcount=0;\n"
+ "          \n"
+ "        } else if (c != '\\r') {\n"
+ "          currentLineIsBlank = false;\n"
+ "        }\n"
+ "      }\n"
+ "    }\n"
+ "    delay(1); \n"
+ "    client.stop();\n"
+ "    Serial.println(\"client disonnected\");\n"
+ "}\n";

                return ret;
	}

}
