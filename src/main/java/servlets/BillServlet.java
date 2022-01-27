package servlets;


import data.Data;
import models.Bill;
import models.Product;
import service.impl.BillCreatorImpl;
import service.impl.BillToPdfImpl;
import service.impl.InputDataHandlerImpl;
import service.interfaces.BillCreator;
import service.interfaces.BillToPdf;
import service.interfaces.InputDataHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class BillServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String args[] = req.getParameter("args").split(" ");

        InputDataHandler dataHandler = new InputDataHandlerImpl();

        Data.init(dataHandler.getFile(args));

        BillToPdf billToPdf = new BillToPdfImpl();

        ArrayList<Product> products = dataHandler.getProducts(args);

        Bill bill = new Bill(dataHandler.getCard(args), products);

        billToPdf.createPdf(bill);

        session.setAttribute("bill", bill);

        req.getRequestDispatcher("/pdf-bill.jsp").forward(req,resp);

    }
}
