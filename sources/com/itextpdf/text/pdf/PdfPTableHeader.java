package com.itextpdf.text.pdf;

public class PdfPTableHeader extends PdfPTableBody {
    protected PdfName role = PdfName.THEAD;

    @Override // com.itextpdf.text.pdf.interfaces.IAccessibleElement, com.itextpdf.text.pdf.PdfPTableBody
    public PdfName getRole() {
        return this.role;
    }

    @Override // com.itextpdf.text.pdf.interfaces.IAccessibleElement, com.itextpdf.text.pdf.PdfPTableBody
    public void setRole(PdfName pdfName) {
        this.role = pdfName;
    }
}
