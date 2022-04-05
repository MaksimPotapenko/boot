/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Brand;
import entity.Sneaker;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import facade.BrandFacade;
import facade.SneakerFacade;

/** 
 *
 * @author user
 */
@WebServlet(name = "MyServlet",loadOnStartup = 1, urlPatterns = {
    "/addSneaker",
    "/createSneaker",
    "/listBoots",
})
public class SneakerServlet extends HttpServlet {
    @EJB private BrandFacade brandFacade;
    @EJB private SneakerFacade sneakerFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        switch (path) {
            case "/addSneaker":
                request.setAttribute("activeAddSneaker", true);
                List<Brand> brands = brandFacade.findAll();
                request.setAttribute("brands", brands);
                request.getRequestDispatcher("/WEB-INF/addSneaker.jsp").forward(request, response);
                break;
            case "/createSneaker":
                String sneakerModel = request.getParameter("sneakerModel");
                String sneakerFirm = request.getParameter("sneakerFirm");
                String[] brandsId = request.getParameterValues("brandsId");
                String sneakerSize = request.getParameter("sneakerSize");
                String sneakerPrice = request.getParameter("sneakerPrice");
                String quantity = request.getParameter("quantity");
                if("".equals(sneakerFirm) || "".equals(sneakerSize) || "".equals(quantity)){
                    request.setAttribute("sneakerModel", sneakerModel);
                    request.setAttribute("sneakerFirm", sneakerFirm);
                    request.setAttribute("sneakerSize", sneakerSize);
                    request.setAttribute("sneakerPrice", sneakerSize);
                    request.setAttribute("quantity", quantity);
                    request.setAttribute("info", "Заполните все поля");
                    request.getRequestDispatcher("/addSneaker").forward(request, response);
                    break;
                }
                if(brandsId == null || brandsId.length == 0){
                    request.setAttribute("sneakerModel", sneakerModel);
                    request.setAttribute("sneakerFirm", sneakerFirm);
                    request.setAttribute("sneakerSize", sneakerSize);
                    request.setAttribute("sneakerPrice", sneakerSize);
                    request.setAttribute("quantity", quantity);
                    request.setAttribute("info", "Выберите бренд");
                    request.getRequestDispatcher("/addSneaker").forward(request, response);
                    break;
                }
                Sneaker sneaker = new Sneaker();
                sneaker.setSneakerModel(sneakerModel);
                List<Brand> listBrands = new ArrayList<>();
                for (int i = 0; i < brandsId.length; i++) {
                    listBrands.add(brandFacade.find(Long.parseLong(brandsId[i])));
        
                }
                sneaker.setBrands(listBrands);
                try {
                    sneaker.setSneakerPrice(Integer.parseInt(sneakerPrice));
                    sneaker.setSneakerQuantity(Integer.parseInt(quantity));
                } catch (Exception e) {
                    request.setAttribute("sneakerFirm", sneakerFirm);
                    request.setAttribute("sneakerSize", sneakerSize);
                    request.setAttribute("quantity", quantity);
                    request.setAttribute("info", "Размер и количество цифрами");
                    request.getRequestDispatcher("/addSneaker").forward(request, response);
                    break;
                }
                sneaker.setCount(sneaker.getSneakerQuantity());
                sneakerFacade.create(sneaker);
                request.setAttribute("info", "Обувь добавлена");
                request.getRequestDispatcher("/listBoots").forward(request, response);
                break;
            case "/listBooks":
                List<Sneaker> sneakers = sneakerFacade.findAll();
                request.setAttribute("sneakers", sneakers);
                request.getRequestDispatcher("/listBoots.jsp").forward(request, response);
                break;
            
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}