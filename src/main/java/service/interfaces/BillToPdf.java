package service.interfaces;

import models.Bill;

public interface BillToPdf {
    void createPdf(Bill bill);
}
