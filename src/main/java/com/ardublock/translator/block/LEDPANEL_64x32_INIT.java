package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class LEDPANEL_64x32_INIT extends TranslatorBlock
        {

                public LEDPANEL_64x32_INIT(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
                {
                        super(blockId, translator, codePrefix, codeSuffix, label);
                }

		private final static String structLGFXHUB75 = 
"	struct LGFX_HUB75 : public lgfx::LGFX_Device\n"+
"{\n"+
"  static void convertCoordinate(uint_fast16_t &x, uint_fast16_t &y)\n"+
"  {\n"+
"    if (0==(x & 8)) { x = x ^ 7; }\n"+
"    /*\n"+
"    if ( x < 256)\n"+
"    {\n"+
"      x = x ^ 127;\n"+
"      y = y ^ 63;\n"+
"    }\n"+
"    */\n"+
"  }\n"+
"\n"+
"  lgfx::Bus_HUB75 _bus_instance;\n"+
"// 1枚だけで使用する場合はこちら\n"+
"  lgfx::Panel_HUB75 _panel_instance;\n"+
"  \n"+
"  LGFX_HUB75(void)\n"+
"  {\n"+
"    // X 座標が8ドット単位で逆順になるパネルの場合、座標変換関数を使用するよう指定する\n"+
"    _panel_instance.convertCoordinate = convertCoordinate;\n"+
"    \n"+
"    {\n"+
"      auto cfg = _bus_instance.config();\n"+
"      cfg.port = 1;\n"+
"   \n"+
"   // RGB configuration\n"+
"      cfg.pin_r1 = GPIO_NUM_33; // R1\n"+
"      cfg.pin_r2 = GPIO_NUM_18; // R2\n"+
"      cfg.pin_g1 = GPIO_NUM_32; // G1\n"+
"      cfg.pin_g2 = GPIO_NUM_19; // G2\n"+
"      cfg.pin_b1 = GPIO_NUM_25; // B1\n"+
"      cfg.pin_b2 = GPIO_NUM_5 ; // B2\n"+
"  /*/ // BRG configuration\n"+
"      cfg.pin_r1 = GPIO_NUM_32; // R1\n"+
"      cfg.pin_r2 = GPIO_NUM_19; // R2\n"+
"      cfg.pin_g1 = GPIO_NUM_25; // G1\n"+
"      cfg.pin_g2 = GPIO_NUM_5 ; // G2\n"+
"      cfg.pin_b1 = GPIO_NUM_33; // B1\n"+
"      cfg.pin_b2 = GPIO_NUM_18; // B2\n"+
"  //*/\n"+
"      cfg.pin_lat = GPIO_NUM_17; // LAT\n"+
"      cfg.pin_oe  = GPIO_NUM_16; // OE\n"+
"      cfg.pin_clk = GPIO_NUM_4 ; // CLK\n"+
"\n"+
"      cfg.pin_addr_a = GPIO_NUM_12;\n"+
"      cfg.pin_addr_b = GPIO_NUM_14;\n"+
"      cfg.pin_addr_c = GPIO_NUM_27;\n"+
"      cfg.pin_addr_d = GPIO_NUM_26;\n"+
"      cfg.pin_addr_e = GPIO_NUM_2;\n"+
"\n"+
"      // 1秒間の更新回数を設定\n"+
"     // cfg.refresh_rate = 300;\n"+
"\n"+
"      // パネルの行選択の仕様に応じて指定する\n"+
"      // cfg.address_mode = cfg.address_shiftreg;\n"+
"      cfg.address_mode = cfg.address_binary;\n"+
"\n"+
"      // LEDドライバの初期化コマンドを指定する\n"+
"      // cfg.initialize_mode = cfg.initialize_none;\n"+
"      cfg.initialize_mode = cfg.initialize_fm6124;\n"+
"\n"+
"      // DMA用のタスクの優先度 (FreeRTOSのタスク機能を使用)\n"+
"      cfg.task_priority = 2;\n"+
"\n"+
"      // DMA用のタスクに使用するCPUコア設定 (FreeRTOSのタスク機能を使用)\n"+
"      cfg.task_pinned_core = PRO_CPU_NUM;\n"+
"      // cfg.task_pinned_core = APP_CPU_NUM;\n"+
"\n"+
"      _bus_instance.config(cfg);\n"+
"      _panel_instance.setBus(&_bus_instance);\n"+
"    }\n"+
"\n"+
"    {\n"+
"      auto cfg = _panel_instance.config();\n"+
"\n"+
 "     // ここでパネルサイズを指定する\n"+
 "     // 複数枚並べる場合は全体の縦横サイズを指定\n"+
"      //  ----- 1枚構成  ここから\n"+
"      cfg.memory_width  = cfg.panel_width  = 64;\n"+
"      cfg.memory_height = cfg.panel_height = 32;\n"+
"\n"+
"      _panel_instance.config(cfg);\n"+
"      setPanel(&_panel_instance);\n"+
"\n"+
"    }\n"+
"\n"+
"  }\n"+
"};\n"+
"\n"+
"LGFX_HUB75 display;\n"+
"\n"+
"#define CLK_PULSE digitalWrite(cfg.pin_clk, HIGH); digitalWrite(cfg.pin_clk, LOW);\n"+
"\n"+
"\n"+
"//===========================================================\n"+
"//	clearLED() ： clear LED panel\n"+
"//===========================================================\n"+
"void clearLED()\n"+
"{\n"+
"  display.setFont(&fonts::Font0);\n"+
"  display.setCursor(0, 0);\n"+
"  display.fillScreen(TFT_BLACK);\n"+
"  display.setTextWrap(false);\n"+
"  display.setTextScroll(true);\n"+
"  display.setTextColor(TFT_WHITE, TFT_BLACK);\n"+
"  display.setTextSize(1.0, 1.0);\n"+
"\n"+
"}\n"+
"\n"+
"static LGFX_Sprite sprite1(&display);\n"+
"static LGFX_Sprite sprite2(&display);\n"+
"static LGFX_Sprite sprite3(&display);\n"+
"char buffer[200];   // ごみの日表示文字列格納用\n";

                @Override
                public String toCode() throws SocketNullException {
			String ret;
  			translator.addSetupCommand("display.init();");
  			translator.addSetupCommand("display.setBrightness(200);");
  			translator.addSetupCommand("display.setColorDepth(16);");
 			 translator.addSetupCommand("display.setSwapBytes(true);");
 			 translator.addSetupCommand("sprite1.createSprite(64,8);");
 			 translator.addSetupCommand("sprite2.createSprite(64,12);");
  			translator.addSetupCommand("sprite3.createSprite(256,12);");
                	translator.addDefinitionCommand("#define PANEL_64x32");
                	translator.addDefinitionCommand("#define LGFX_USE_V1");
                	translator.addDefinitionCommand(structLGFXHUB75);
                	translator.addHeaderFile("LovyanGFX.hpp");

/*
ret = " sprite1.fillRect(0,0,200,60,display.color888(0,0,0));\n"+
  "\tsprite1.setFont(&fonts::Font0);\n"+
  "\tsprite1.setTextColor(display.color888(0,0,255));\n"+
  "\tsprite1.setCursor(0,1);\n"+
  "\tsprite1.printf(\"%02d/%02d/%02d\",Year,Month,Day);\n"+
  "\tsprite1.pushSprite(2,0);\n"+

  "\tsprite2.fillRect(0,0,200,60,display.color888(0,50,0));\n"+
  "\tsprite2.setFont(&fonts::Font8x8C64);\n"+
  "\tsprite2.setFont(&fonts::DejaVu12);\n"+
  "\tsprite2.setFont(&efontJA_16_b);\n"+
  "\tsprite2.setTextColor(display.color888(255,255,255));\n"+
  "\tsprite2.setCursor(0,-3 );\n"+
  "\tsprite2.printf(\"%02d:%02d:%02d\",Hour,Min,Sec);\n"+
  "\tsprite2.pushSprite(0,8);";
*/
		ret = "";
  		return codePrefix + ret + codeSuffix;
                }

        }


