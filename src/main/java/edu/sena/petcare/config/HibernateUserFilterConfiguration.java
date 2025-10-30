package edu.sena.petcare.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Filter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.lang.NonNull;

import java.io.IOException;

@Component
public class HibernateUserFilterConfiguration extends OncePerRequestFilter {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, 
                                    @NonNull HttpServletResponse response, 
                                    @NonNull FilterChain filterChain)
        throws ServletException, IOException {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = null;
        try {
            filter = session.enableFilter("activeUsersFilter");
            filter.setParameter("isDeleted", false);
            filterChain.doFilter(request, response);
        } finally {
            if (filter != null) {
                session.disableFilter("activeUsersFilter");
            }
        }
    }
}


