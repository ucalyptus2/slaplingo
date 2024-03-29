package com.itextpdf.xmp.impl;

import com.itextpdf.xmp.XMPException;

/* compiled from: ISO8601Converter */
class ParseState {
    private int pos = 0;
    private String str;

    public ParseState(String str2) {
        this.str = str2;
    }

    public int length() {
        return this.str.length();
    }

    public boolean hasNext() {
        return this.pos < this.str.length();
    }

    public char ch(int i) {
        if (i < this.str.length()) {
            return this.str.charAt(i);
        }
        return 0;
    }

    public char ch() {
        if (this.pos < this.str.length()) {
            return this.str.charAt(this.pos);
        }
        return 0;
    }

    public void skip() {
        this.pos++;
    }

    public int pos() {
        return this.pos;
    }

    public int gatherInt(String str2, int i) throws XMPException {
        char ch = ch(this.pos);
        int i2 = 0;
        boolean z = false;
        while ('0' <= ch && ch <= '9') {
            i2 = (i2 * 10) + (ch - '0');
            int i3 = this.pos + 1;
            this.pos = i3;
            ch = ch(i3);
            z = true;
        }
        if (!z) {
            throw new XMPException(str2, 5);
        } else if (i2 > i) {
            return i;
        } else {
            if (i2 < 0) {
                return 0;
            }
            return i2;
        }
    }
}
