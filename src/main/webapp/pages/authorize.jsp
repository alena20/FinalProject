<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="card col-5 offset-2">
    <h5 class="card-header">Регистрация</h5>
    <div class="card-body">
        <div class="panel panel-default">
            <div class="panel-body">
                <form method="post" action="${pageContext.request.contextPath}/user">
                    <div class="form-row">
                        <label for="login" class="col-5">Логин</label>
                        <div class="col-7">
                            <input type="text" class="form-control" id="login" pattern="^[a-zA-Zа-яА-Я][a-zA-Zа-яА-Я0-9-_\.]{1,20}$"
                                   name="login" required style="margin-bottom: 10px">
                        </div>
                    </div>
                    <div class="form-row">
                        <label for="pass" class="col-5">Пароль</label>
                        <div class="col-7">
                            <input type="password" class="form-control" id="pass" pattern="^[a-zA-Zа-яА][a-zA-Z0-9-_\.]{1,20}$"
                                   name="pass" required style="margin-bottom: 10px">
                        </div>
                        <label for="pass" class="col-5">Повторите пароль</label>
                        <div class="col-7">
                            <input type="password" class="form-control" id="passconf" pattern="^[a-zA-Zа-яА][a-zA-Z0-9-_\.]{1,20}$"
                                   name="passconf" required onChange="checkPasswordMatch();">
                        </div>
                        <div class="registrationFormAlert" id="divCheckPasswordMatch"></div>
                        <script>
                            function checkPasswordMatch() {
                                var password = $("#pass").val();
                                var confirmPassword = $("#passconf").val();

                                if (password != confirmPassword)
                                    $("#divCheckPasswordMatch").html("Passwords do not match!");
                                else
                                    $("#divCheckPasswordMatch").html("Passwords match.");
                            }
                        </script>
                    </div>
                    <input type="hidden" name="action" value="REGISTRATION">
                    <br>
                    <button class="btn btn-primary col-5 offset-4" type="submit">Регистрация</button>
                </form>
            </div>
        </div>
    </div>
</div>