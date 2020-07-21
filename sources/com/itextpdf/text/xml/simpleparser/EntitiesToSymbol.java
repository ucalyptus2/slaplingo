package com.itextpdf.text.xml.simpleparser;

import androidx.exifinterface.media.ExifInterface;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.html.HtmlTags;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.xml.xmp.PdfProperties;
import java.util.HashMap;
import java.util.Map;
import kotlin.text.Typography;
import org.spongycastle.pqc.math.linearalgebra.Matrix;

public class EntitiesToSymbol {
    private static final Map<String, Character> MAP;

    static {
        HashMap hashMap = new HashMap();
        MAP = hashMap;
        hashMap.put("169", (char) 227);
        MAP.put("172", (char) 216);
        MAP.put("174", (char) 210);
        Map<String, Character> map = MAP;
        Character valueOf = Character.valueOf(Typography.plusMinus);
        map.put("177", valueOf);
        MAP.put("215", (char) 180);
        MAP.put("247", (char) 184);
        MAP.put("8230", (char) 188);
        Map<String, Character> map2 = MAP;
        Character valueOf2 = Character.valueOf(Typography.cent);
        map2.put("8242", valueOf2);
        MAP.put("8243", (char) 178);
        MAP.put("8260", (char) 164);
        MAP.put("8364", (char) 240);
        MAP.put("8465", (char) 193);
        Map<String, Character> map3 = MAP;
        Character valueOf3 = Character.valueOf(Barcode128.DEL);
        map3.put("8472", valueOf3);
        MAP.put("8476", (char) 194);
        MAP.put("8482", (char) 212);
        MAP.put("8501", (char) 192);
        MAP.put("8592", (char) 172);
        MAP.put("8593", (char) 173);
        MAP.put("8594", Character.valueOf(Typography.registered));
        MAP.put("8595", (char) 175);
        MAP.put("8596", Character.valueOf(Typography.leftGuillemete));
        MAP.put("8629", (char) 191);
        MAP.put("8656", (char) 220);
        MAP.put("8657", (char) 221);
        MAP.put("8658", (char) 222);
        MAP.put("8659", (char) 223);
        MAP.put("8660", (char) 219);
        MAP.put("8704", Character.valueOf(Typography.quote));
        MAP.put("8706", Character.valueOf(Typography.paragraph));
        MAP.put("8707", Character.valueOf(Typography.dollar));
        MAP.put("8709", Character.valueOf(Barcode128.SHIFT));
        MAP.put("8711", (char) 209);
        MAP.put("8712", (char) 206);
        MAP.put("8713", (char) 207);
        MAP.put("8717", '\'');
        MAP.put("8719", (char) 213);
        MAP.put("8721", (char) 229);
        MAP.put("8722", '-');
        MAP.put("8727", '*');
        MAP.put("8729", Character.valueOf(Typography.middleDot));
        MAP.put("8730", (char) 214);
        MAP.put("8733", (char) 181);
        MAP.put("8734", (char) 165);
        MAP.put("8736", (char) 208);
        MAP.put("8743", (char) 217);
        MAP.put("8744", (char) 218);
        MAP.put("8745", Character.valueOf(Barcode128.CODE_C));
        MAP.put("8746", (char) 200);
        MAP.put("8747", (char) 242);
        MAP.put("8756", '\\');
        MAP.put("8764", '~');
        MAP.put("8773", '@');
        MAP.put("8776", Character.valueOf(Typography.rightGuillemete));
        MAP.put("8800", (char) 185);
        MAP.put("8801", (char) 186);
        MAP.put("8804", Character.valueOf(Typography.pound));
        MAP.put("8805", (char) 179);
        MAP.put("8834", Character.valueOf(Barcode128.STARTB));
        MAP.put("8835", (char) 201);
        MAP.put("8836", Character.valueOf(Barcode128.STARTA));
        MAP.put("8838", Character.valueOf(Barcode128.STARTC));
        MAP.put("8839", Character.valueOf(Barcode128.FNC1));
        MAP.put("8853", Character.valueOf(Barcode128.FNC2));
        MAP.put("8855", Character.valueOf(Barcode128.FNC3));
        MAP.put("8869", '^');
        MAP.put("8901", Character.valueOf(Typography.times));
        MAP.put("8992", (char) 243);
        MAP.put("8993", (char) 245);
        MAP.put("9001", (char) 225);
        MAP.put("9002", (char) 241);
        MAP.put("913", 'A');
        MAP.put("914", 'B');
        MAP.put("915", 'G');
        MAP.put("916", 'D');
        MAP.put("917", 'E');
        MAP.put("918", Character.valueOf(Matrix.MATRIX_TYPE_ZERO));
        MAP.put("919", 'H');
        MAP.put("920", 'Q');
        MAP.put("921", 'I');
        MAP.put("922", 'K');
        MAP.put("923", Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_LT));
        MAP.put("924", 'M');
        MAP.put("925", 'N');
        MAP.put("926", 'X');
        MAP.put("927", 'O');
        MAP.put("928", 'P');
        MAP.put("929", Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_REGULAR));
        MAP.put("931", 'S');
        MAP.put("932", 'T');
        MAP.put("933", Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_UT));
        MAP.put("934", 'F');
        MAP.put("935", 'C');
        MAP.put("936", 'Y');
        MAP.put("937", 'W');
        MAP.put("945", 'a');
        MAP.put("946", 'b');
        MAP.put("947", Character.valueOf(Barcode128.START_A));
        MAP.put("948", Character.valueOf(Barcode128.CODE_AC_TO_B));
        MAP.put("949", Character.valueOf(Barcode128.CODE_BC_TO_A));
        MAP.put("950", 'z');
        MAP.put("951", Character.valueOf(Barcode128.START_B));
        MAP.put("952", 'q');
        MAP.put("953", Character.valueOf(Barcode128.START_C));
        MAP.put("954", 'k');
        MAP.put("955", 'l');
        MAP.put("956", 'm');
        MAP.put("957", 'n');
        MAP.put("958", 'x');
        MAP.put("959", 'o');
        MAP.put("960", 'p');
        MAP.put("961", 'r');
        MAP.put("962", 'V');
        MAP.put("963", 's');
        MAP.put("964", 't');
        MAP.put("965", 'u');
        MAP.put("966", Character.valueOf(Barcode128.FNC1_INDEX));
        MAP.put("967", Character.valueOf(Barcode128.CODE_AB_TO_C));
        MAP.put("9674", (char) 224);
        MAP.put("968", 'y');
        MAP.put("969", 'w');
        MAP.put("977", 'J');
        MAP.put("978", (char) 161);
        MAP.put("981", 'j');
        MAP.put("982", 'v');
        MAP.put("9824", (char) 170);
        MAP.put("9827", Character.valueOf(Typography.section));
        MAP.put("9829", Character.valueOf(Typography.copyright));
        MAP.put("9830", (char) 168);
        MAP.put("Alpha", 'A');
        MAP.put("Beta", 'B');
        MAP.put("Chi", 'C');
        MAP.put("Delta", 'D');
        MAP.put("Epsilon", 'E');
        MAP.put("Eta", 'H');
        MAP.put(ExifInterface.TAG_GAMMA, 'G');
        MAP.put("Iota", 'I');
        MAP.put("Kappa", 'K');
        MAP.put("Lambda", Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_LT));
        MAP.put("Mu", 'M');
        MAP.put("Nu", 'N');
        MAP.put("Omega", 'W');
        MAP.put("Omicron", 'O');
        MAP.put("Phi", 'F');
        MAP.put("Pi", 'P');
        MAP.put("Prime", (char) 178);
        MAP.put("Psi", 'Y');
        MAP.put("Rho", Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_REGULAR));
        MAP.put("Sigma", 'S');
        MAP.put("Tau", 'T');
        MAP.put("Theta", 'Q');
        MAP.put("Upsilon", Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_UT));
        MAP.put("Xi", 'X');
        MAP.put("Zeta", Character.valueOf(Matrix.MATRIX_TYPE_ZERO));
        MAP.put("alefsym", (char) 192);
        MAP.put("alpha", 'a');
        MAP.put("and", (char) 217);
        MAP.put("ang", (char) 208);
        MAP.put("asymp", Character.valueOf(Typography.rightGuillemete));
        MAP.put("beta", 'b');
        MAP.put("cap", Character.valueOf(Barcode128.CODE_C));
        MAP.put("chi", Character.valueOf(Barcode128.CODE_AB_TO_C));
        MAP.put("clubs", Character.valueOf(Typography.section));
        MAP.put("cong", '@');
        MAP.put("copy", (char) 211);
        MAP.put("crarr", (char) 191);
        MAP.put("cup", (char) 200);
        MAP.put("dArr", (char) 223);
        MAP.put("darr", (char) 175);
        MAP.put("delta", Character.valueOf(Barcode128.CODE_AC_TO_B));
        MAP.put("diams", (char) 168);
        MAP.put("divide", (char) 184);
        MAP.put("empty", Character.valueOf(Barcode128.SHIFT));
        MAP.put("epsilon", Character.valueOf(Barcode128.CODE_BC_TO_A));
        MAP.put("equiv", (char) 186);
        MAP.put("eta", Character.valueOf(Barcode128.START_B));
        MAP.put("euro", (char) 240);
        MAP.put("exist", Character.valueOf(Typography.dollar));
        MAP.put("forall", Character.valueOf(Typography.quote));
        MAP.put("frasl", (char) 164);
        MAP.put("gamma", Character.valueOf(Barcode128.START_A));
        MAP.put("ge", (char) 179);
        MAP.put("hArr", (char) 219);
        MAP.put("harr", Character.valueOf(Typography.leftGuillemete));
        MAP.put("hearts", Character.valueOf(Typography.copyright));
        MAP.put("hellip", (char) 188);
        MAP.put("horizontal arrow extender", (char) 190);
        MAP.put("image", (char) 193);
        MAP.put("infin", (char) 165);
        MAP.put("int", (char) 242);
        MAP.put("iota", Character.valueOf(Barcode128.START_C));
        MAP.put("isin", (char) 206);
        MAP.put("kappa", 'k');
        MAP.put("lArr", (char) 220);
        MAP.put("lambda", 'l');
        MAP.put("lang", (char) 225);
        MAP.put("large brace extender", (char) 239);
        MAP.put("large integral extender", (char) 244);
        MAP.put("large left brace (bottom)", (char) 238);
        MAP.put("large left brace (middle)", (char) 237);
        MAP.put("large left brace (top)", (char) 236);
        MAP.put("large left bracket (bottom)", (char) 235);
        MAP.put("large left bracket (extender)", (char) 234);
        MAP.put("large left bracket (top)", (char) 233);
        MAP.put("large left parenthesis (bottom)", (char) 232);
        MAP.put("large left parenthesis (extender)", (char) 231);
        MAP.put("large left parenthesis (top)", (char) 230);
        MAP.put("large right brace (bottom)", (char) 254);
        MAP.put("large right brace (middle)", (char) 253);
        MAP.put("large right brace (top)", (char) 252);
        MAP.put("large right bracket (bottom)", (char) 251);
        MAP.put("large right bracket (extender)", (char) 250);
        MAP.put("large right bracket (top)", (char) 249);
        MAP.put("large right parenthesis (bottom)", (char) 248);
        MAP.put("large right parenthesis (extender)", (char) 247);
        MAP.put("large right parenthesis (top)", (char) 246);
        MAP.put("larr", (char) 172);
        MAP.put("le", Character.valueOf(Typography.pound));
        MAP.put("lowast", '*');
        MAP.put("loz", (char) 224);
        MAP.put("minus", '-');
        MAP.put("mu", 'm');
        MAP.put("nabla", (char) 209);
        MAP.put("ne", (char) 185);
        MAP.put("not", (char) 216);
        MAP.put("notin", (char) 207);
        MAP.put("nsub", Character.valueOf(Barcode128.STARTA));
        MAP.put("nu", 'n');
        MAP.put("omega", 'w');
        MAP.put("omicron", 'o');
        MAP.put("oplus", Character.valueOf(Barcode128.FNC2));
        MAP.put("or", (char) 218);
        MAP.put("otimes", Character.valueOf(Barcode128.FNC3));
        MAP.put(PdfProperties.PART, Character.valueOf(Typography.paragraph));
        MAP.put("perp", '^');
        MAP.put("phi", Character.valueOf(Barcode128.FNC1_INDEX));
        MAP.put("pi", 'p');
        MAP.put("piv", 'v');
        MAP.put("plusmn", valueOf);
        MAP.put("prime", valueOf2);
        MAP.put("prod", (char) 213);
        MAP.put("prop", (char) 181);
        MAP.put("psi", 'y');
        MAP.put("rArr", (char) 222);
        MAP.put("radic", (char) 214);
        MAP.put("radical extender", '`');
        MAP.put("rang", (char) 241);
        MAP.put("rarr", Character.valueOf(Typography.registered));
        MAP.put("real", (char) 194);
        MAP.put("reg", (char) 210);
        MAP.put("rho", 'r');
        MAP.put("sdot", Character.valueOf(Typography.times));
        MAP.put("sigma", 's');
        MAP.put("sigmaf", 'V');
        MAP.put("sim", '~');
        MAP.put("spades", (char) 170);
        MAP.put(HtmlTags.SUB, Character.valueOf(Barcode128.STARTB));
        MAP.put("sube", Character.valueOf(Barcode128.STARTC));
        MAP.put("sum", (char) 229);
        MAP.put(HtmlTags.SUP, (char) 201);
        MAP.put("supe", Character.valueOf(Barcode128.FNC1));
        MAP.put("tau", 't');
        MAP.put("there4", '\\');
        MAP.put("theta", 'q');
        MAP.put("thetasym", 'J');
        MAP.put("times", (char) 180);
        MAP.put("trade", (char) 212);
        MAP.put("uArr", (char) 221);
        MAP.put("uarr", (char) 173);
        MAP.put("upsih", (char) 161);
        MAP.put("upsilon", 'u');
        MAP.put("vertical arrow extender", Character.valueOf(Typography.half));
        MAP.put("weierp", valueOf3);
        MAP.put("xi", 'x');
        MAP.put("zeta", 'z');
    }

    public static Chunk get(String str, Font font) {
        char correspondingSymbol = getCorrespondingSymbol(str);
        if (correspondingSymbol == 0) {
            try {
                return new Chunk(String.valueOf((char) Integer.parseInt(str)), font);
            } catch (Exception unused) {
                return new Chunk(str, font);
            }
        } else {
            return new Chunk(String.valueOf(correspondingSymbol), new Font(Font.FontFamily.SYMBOL, font.getSize(), font.getStyle(), font.getColor()));
        }
    }

    public static char getCorrespondingSymbol(String str) {
        Character ch = MAP.get(str);
        if (ch == null) {
            return 0;
        }
        return ch.charValue();
    }
}