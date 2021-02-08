<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="card col-5 offset-2">
    <h5 class="card-header">Вход</h5>
    <div class="card-body">
        <div class="panel panel-default">
            <div class="panel-body">
                <form method="post" action="${pageContext.request.contextPath}/user">
                    <div class="form-row">
                        <label for="login" class="col-3">Логин</label>
                        <div class="col-9">
                            <input type="text" class="form-control" id="login" pattern="^[a-zA-Zа-яА-Я][a-zA-Zа-яА-Я0-9-_\.]{1,20}$"
                                   name="login" required style="margin-bottom: 10px">
                        </div>
                    </div>
                    <div class="form-row">
                        <label for="pass" class="col-3">Пароль</label>
                        <div class="col-9">
                            <input type="password" class="form-control" id="pass"
                                   pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$"
                                   name="pass" required>
                        </div>
                    </div>
                    <br>
                    <input type="hidden" name="action" value="LOGIN">
                    <button class="btn btn-primary col-5 offset-4" type="submit">Войти</button>
                    <br>
                </form>
                <form class="form-inline my-2 my-lg-0">
                    <button class="btn btn-primary col-5 offset-4" type="submit" formmethod="GET"
                            formaction="${pageContext.request.contextPath}/user" name="action" value="REGISTRATION_FORM">
                        Зарегистрироваться
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>


