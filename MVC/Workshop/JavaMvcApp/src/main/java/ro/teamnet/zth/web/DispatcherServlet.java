package ro.teamnet.zth.web;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.appl.controller.DepartmentController;
import ro.teamnet.zth.appl.controller.EmployeeController;
import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.*;
import java.util.HashMap;

public class DispatcherServlet extends HttpServlet {

    HashMap<String, MethodAttributes>  allowedMethods = new HashMap<>();

    @Override
    public void init() throws ServletException {
        try {
            Iterable<Class> controllers = AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.controller");
            for (Class controller:controllers)
            {
                if(controller.isAnnotationPresent(MyController.class)){
                    MyController myCtrlAnn = (MyController)controller.getAnnotation(MyController.class);
                    String ctrlUrlPath = myCtrlAnn.urlPath();

                    Method[] ctrlMethods = controller.getMethods();
                    for (Method ctrlMethod:ctrlMethods) {
                        if(ctrlMethod.isAnnotationPresent(MyRequestMethod.class)){
                            {
                                MyRequestMethod myReqAnn = ctrlMethod.getAnnotation(MyRequestMethod.class);
                                String methodUrlPath = myReqAnn.urlPath();

                                String urlPath = ctrlUrlPath + methodUrlPath;
                                MethodAttributes methodAttributes = new MethodAttributes();
                                methodAttributes.setControllerClass(controller.getName());
                                methodAttributes.setMethodType(myReqAnn.methodType());
                                methodAttributes.setMethodName(ctrlMethod.getName());
                                allowedMethods.put(urlPath, methodAttributes);
                            }
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //instructiuni de delegare
        dispatchReply("GET", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //instructiuni de delegare
        dispatchReply("POST", req, resp);
    }

    private void dispatchReply(String requestType, HttpServletRequest request, HttpServletResponse response) throws ServletException {

        Object r = null;

        try {
        r = dispatch(request, response);}
        catch (Exception exception){
            sendExceptionError(exception , request, response);
        }
        try {
            reply(r, request, response);
        } catch (IOException exception) {
            sendExceptionError(exception , request, response);
        }
    }

    private Object dispatch(HttpServletRequest req, HttpServletResponse resp){

        String path = req.getPathInfo();
        MethodAttributes methodAttributes = allowedMethods.get(path);

        if(methodAttributes == null){
            return "No Method for given link!";
        }

        String ctrlName = methodAttributes.getControllerClass();
        try {
            Class<?> ctrlClass = Class.forName(ctrlName);
            Object ctrlInstance = ctrlClass.newInstance();
            Method method = ctrlClass.getMethod(methodAttributes.getMethodName());
            Object result = method.invoke(ctrlInstance);
            return result;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return "Internal Error!";
    }

    private void reply(Object obj, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.printf(obj.toString());
    }

    private void sendExceptionError(Exception exception, HttpServletRequest req, HttpServletResponse resp){

    }



}
