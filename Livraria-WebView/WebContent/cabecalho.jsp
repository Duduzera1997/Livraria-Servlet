<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title> .:: Livraria ::. </title>
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    </head>

    <body>
        <div id="page-top-outer">  
            <div class="top-page">
                <table width="100%" border="0">
                    <tr class="cabecalho">
                        <td width="12%">
                            <div align="center">
                                <a href="lstprodutos.jsp" class="forgot-pwd">Pesquisar Produtos</a>
                            </div>
                        </td>
                        <td width="12%">
                            <div align="center">
                                <a href="servletproduto?action=novo" class="forgot-pwd">Cadastro de Produtos</a>
                            </div>
                        </td>
                        <td width="12%">
                            <div align="center">
                                <a href="lstfornecedores.jsp" class="forgot-pwd">Pesquisar Fornecedores</a>
                            </div>
                        </td>
                        <td width="12%">
                            <div align="center">
                                <a href="lstentrada.jsp" class="forgot-pwd">Pesquisar Entrada</a>
                            </div>
                        </td>
                        
                        <td width="12%">
                            <div align="center">
                                <a href="frmvenda.jsp" class="forgot-pwd">Vendas</a>
                            </div>
                        </td>
                        <td width="12%">
                            <div class="top-page" align="center">
                                <form action="servletuser" method="post" name="frmLogin">
                                    <input type="hidden" name="action" value="logout">
                                    <a href="#" onclick="document.frmLogin.submit()" class="forgot-pwd">Sair</a>
                                </form>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html>