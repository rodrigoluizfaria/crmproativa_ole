package com.proativaservicos.exception;

import jakarta.faces.FacesException;
import jakarta.faces.application.ViewExpiredException;
import jakarta.faces.context.ExceptionHandler;
import jakarta.faces.context.ExceptionHandlerWrapper;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ExceptionQueuedEvent;
import jakarta.faces.event.ExceptionQueuedEventContext;

import java.io.IOException;
import java.util.Iterator;

public class JsfExceptionHandler extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;

    @SuppressWarnings("deprecation")
    public JsfExceptionHandler(ExceptionHandler wrapped) {

        this.wrapped = wrapped;

    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws FacesException {

        Iterator<ExceptionQueuedEvent> listEvents = getUnhandledExceptionQueuedEvents().iterator();

        while (listEvents.hasNext()) {

            ExceptionQueuedEvent event = listEvents.next();
            ExceptionQueuedEventContext contex = (ExceptionQueuedEventContext) event.getSource();

            Throwable exception = contex.getException();

            try {

                if (exception instanceof ViewExpiredException) {

                    System.err.println("erro.....");
                    redirect("/");

                }

            } finally {

                listEvents.remove();

            }
        }

        getWrapped().handle();

    }

    private void redirect(String page) {

        try {

            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContex = context.getExternalContext();
            String contexPath = externalContex.getRequestContextPath();
            System.out.println(contexPath + " ------");
            externalContex.redirect(contexPath + page);
            context.responseComplete();

        } catch (IOException e) {

            throw new FacesException(e);
        }

    }

}
