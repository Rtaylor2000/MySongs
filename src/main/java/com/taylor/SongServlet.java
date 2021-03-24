/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taylor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ryan
 */
@WebServlet(name = "SongServlet", urlPatterns = {"/songs"})
public class SongServlet extends HttpServlet {

    private final Map<Integer, Song> music = new Hashtable<>();
    public SongServlet() {
        Song neverGonnaGiveYouUp = new Song(1, "Never Gonna Give You Up", 
                "Rick Astley", "https://youtu.be/dQw4w9WgXcQ");
        Song togetherForever = new Song(2, "Together Forever", "Rick Astley", 
                "https://youtu.be/yPYZpwSpKmA");
        Song slippingAway = new Song(3, "Slipping Away", "Rick Astley", "https://youtu.be/czDxG-7SFsc");
        Song asYouLikeIt = new Song(4, "As You Like It", "Eve", "https://youtu.be/nROvY9uiYYk");
        Song karma = new Song(5, "Karma", "AJR", "https://youtu.be/Vy1JwiXHwI4");
        music.put(togetherForever.getSongId(), togetherForever);
        music.put(neverGonnaGiveYouUp.getSongId(), neverGonnaGiveYouUp);
        music.put(slippingAway.getSongId(), slippingAway);
        music.put(asYouLikeIt.getSongId(), asYouLikeIt);
        music.put(karma.getSongId(), karma);
    }
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
        HttpSession session = request.getSession();
        if(session.getAttribute("username") == null) {
            response.sendRedirect("login");
            return;
        }
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "browse";
        }

        switch (action) {
            case "addToSongList":
                addToSongList(request, response);
                break;
            case "emptyPlayList":
                emptyPlayList(request, response);
                break;
            case "viewPlayList":
                viewPlayList(request, response);
                break;
            case "removeSong":
                removeSong(request, response);
                break;
            case "browse":
            default:
                browse(request, response);
                break;
        }
    }
    
    private void browse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("music", music);
        request.getRequestDispatcher("/WEB-INF/jsp/view/browse.jsp").forward(request, response);
    }
    
    private void addToSongList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int songId;
        try {
            songId = Integer.parseInt(request.getParameter("songId"));
        } catch (Exception e) {
            response.sendRedirect("songs");
            return;
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new Hashtable<Integer, Song>());
        }

        @SuppressWarnings("unchecked")
        Map<Integer, Song> cart = (Map<Integer, Song>) session.getAttribute("cart");
        if (!cart.containsKey(songId)) {
            cart.put(songId, music.get(songId));
        }
        //cart.put(songId, cart.get(songId) + 1);

        response.sendRedirect("songs?action=viewPlayList");
    }
    
    private void viewPlayList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("songs", music);
        request.getRequestDispatcher("/WEB-INF/jsp/view/viewPlayList.jsp").forward(request, response);
    }
    
    private void emptyPlayList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("cart");
        response.sendRedirect("songs?action=viewPlayList");
    }

    private void removeSong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int songId;
        try {
            songId = Integer.parseInt(request.getParameter("songId"));
        } catch (Exception e) {
            response.sendRedirect("songs?action=viewPlayList");
            return;
        }
        HttpSession session = request.getSession();
        if (session.getAttribute("cart") == null) {
            response.sendRedirect("songs?action=viewPlayList");
            return;
        }
        @SuppressWarnings("unchecked")
        Map<Integer, Song> cart = (Map<Integer, Song>) session.getAttribute("cart");
        if (cart.containsKey(songId)) {
            cart.remove(songId);
        }
        response.sendRedirect("songs?action=viewPlayList");
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
        HttpSession session = request.getSession();
        if(session.getAttribute("username") == null) {
            response.sendRedirect("login");
            return;
        }
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
        HttpSession session = request.getSession();
        if(session.getAttribute("username") == null) {
            response.sendRedirect("login");
            return;
        }
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
