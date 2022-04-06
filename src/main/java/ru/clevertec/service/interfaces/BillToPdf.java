package ru.clevertec.service.interfaces;

import ru.clevertec.models.Bill;

public interface BillToPdf {
    void createPdf(Bill bill);
}
