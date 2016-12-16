O trabalho prático consiste na implementação de um sistema de leilões. O trabalho está organizado nas seguintes três metas:

- Meta 1 - Estrutura de aplicação: EJB e managed beans com acesso remoto consola. 3 de Janeiro 2017
- Meta 2 - Armazenamento de dados JPA.  17 de Janeiro 2017
- Meta 3 - Componente Web JSF.   dia do Exame

O ambiente de referência para o trabalho consiste em primeiro lugar na máquina servidor tal como descrito nos documentos fornecidos, e também no IDE descrito na máquina cliente. Podem ser dadas especificações adicionais grupo a grupo que envolvam nomes de domínio e base de dados.

1 **Descrição geral**
O trabalho prático consiste na implementação de um sistema de leilões. As funcionalidades e conceitos envolvidos são baseados em diversos sistemas reais, apesar de este trabalho ser uma simplificação. As funcionalidades fundamentais serão colocar itens à venda e efectuar licitação de itens, concorrendo com outros utilizadores.
Existem itens e utilizadores. Os itens são colocados à venda e comprados pelos utilizadores. Existe um administrador que configura e administra vários aspectos do sistema (não se trata do administrador da máquina nem do servidor).

**Itens**
Os itens são colocados à venda pelos utilizadores. O item tem um preço inicial, um preço "compre já" e um prazo máximo de venda. O preço "compre já" é um preço que fará com que o item seja vendido de imediato ao utilizador que oferece esse preço. O prazo máximo é o instante em que o leilão desse item encerra sendo atribuído ao utilizador que ofereceu o valor máximo até esse instante. Se nenhum utilizador tiver licitado esse item, a venda dele não é efectuada. A um dado instante um item pode estar a ser seguido por um ou mais utilizadores. Sempre que algo acontece a um determinado item, os utilizadores que o estejam a seguir ou que o estejam a licitar obtêm essa informação (este aspecto depende da meta em que é implementado). As coisas que podem acontecer a um item são, por exemplo, nova licitação, foi arrematado, etc.

O "ciclo de vida" de um item é o seguinte:

- O item foi posto à venda. Está imediatamente em leilão.
- O item deixou de estar à venda por uma das duas razões:
  - Foi "ganho" pelo utilizador que fez a maior oferta ou que ofereceu o valor "compre já". O item continua a existir, mas as licitações para ele deixam de ser consideradas.
  - A venda foi cancelada pelo administrador do sistema, por alguma razão (houve uma denúncia acerca do item).
  - Esgotou-se o prazo e ninguém ofereceu nada
- No caso do Item ter sido vendido, é preciso ainda concluir a transacção, o que envolve pagar pelo item.

O item nunca é esquecido pelo sistema. Isto simplifica a gestão de dependências chave-primária / chave forasteira na base de dados.

Existe uma espécie de newsletter onde são publicadas algumas notícias de carácter geral o que inclui: item ... vendido e por quanto, nova conta criada com username ..., conta com usernarne recusada por motivo de ...., etc. (as operações que são alvo de notícia na newsletter são identificadas neste enunciado). Esta newsletter é acessível a todos, inclusive aos visitantes.

2 Utilizadores
O sistema tem os seguintes tipos de utilizadores:

**Administrador**
 O administrador não vende nem compra itens. Efectua diversas operações de gestão do sistema, e de utilizadores.

**Utilizador**
 Os utilizadores fazem essencialmente a consulta de itens, licitações e colocação de itens à venda. Cada utilizador tem uma "conta" no sistema, onde lhe está associada o histórico de itens vendidos, comprados ou apenas licitados. Um utilizador pode desactivar a sua conta, mas o sistema nunca o esquecerá e a sua conta pode ser reactivada a seu pedido.

**Visitante**
 O visitante ode consultar alguma informação geral sobre o sistema, de uma forma genérica e sem dados específicos de um utilizador. Um visitante pode registar-se no sistema voluntariamente, e após aprovação do administrador, passa a ser utilizador.

O administrador é sempre o administrador. Há sempre um e apenas um administrador. O usernorne do administrador é sempre "admira". A sua password é predefinida no sistema mas pode ser modificada por este segundo a lógica habitual (definir nova password, repetir para confirmar, etc.).

O visitante tem carácter anónimo: não tem usernome nem está sujeito a autenticação. O sistema não distingue um visitante de outro pois não armazena dados relativos a visitantes. Toda utilização do sistema começa por ser segundo a óptica de visitante até que seja fornecida uma credencial username/password válida.

O utilizador tem o seguinte ciclo de vida:

- O visitante preenche um formulário de pedido de adesão onde fornece os seus dados.
  - O administrador, ao consultar os pedidos de adesão, aceita ou não aceita o pedido. Se aceitar é criada a conta. O sistema deve verificar os aspectos essenciais e evidentes tais como a unicidade de username e possword válida.   As contas criadas são alvo de publicação na newsleter do sistema.
  - O novo utilizador toma conhecimento que a sua conta foi ou não criada consultando a newsletter. Também pode 'vir tentando lagar-se".
  - O utilizador pode suspender a sua conta (noticiado na newsletter).
  - O utilizador pode reactivar a sua conta através de formulário de reactivação (noticiado na newsletter).
  - A conta do utilizador pode ser suspensa pelo administrador (noticiado na newsletter).

O sistema nunca esquece (nunca apaga) uma conta de utilizador. Para além de fazer todo o sentido "na vida real", este aspecto simplifica a base de dados.

As funcionalidades principais segundo o ponto de vista de cada um dos utilizadores, são:

**Utilizador**

- Efectua a gestão da sua conta: consulta e actualização dos seus dados pessoais (nome, morada), pedido de suspensão de conta (especificando a razão), 
- Consulta dos seus itens à venda: quais e preço actual e por quem, lista de licitações para um item, etc.
- Consulta mensagens dirigidas a si: os utilizadores podem enviar mensagens uns aos outros.
- Envio de mensagens (ode ser a partir da consulta de um item específico, dirigida ao seu vendedor).
- Coloca itens à venda. O item tem uma descrição, um preço inicial, um preço compre-já, um prazo. Entrende a uma categoria (de entre as definidas pelo administrador).
- Consulta itens por categoria, descrição, preço, prazo.
- Visualiza um item em particular. Nesse contexto pode:
 - Licitar um item
 - Enviar mensagem ao vendedor
 - Definir seguir item / cancelar item seguido.
 - Denunciar o item (especificando a razão), sendo o administrador notificado.
 - Denunciar o vendedor (especificando a razão), sendo o administrador notificado.
- Conclui transacção (pagar por item vendido).
- Consultar itens seguidos (lista ou detalhes de um em particular).
- Lista historial de itens vendidos e comprados (pode ser uma lista simplificada).
- Consulta o seu saldo (moedas virtuais) e pode fazer carregamentos (simplificação da realidade).
- Tem acesso à newsletter (visualização).

**Administrador**

- Consulta notificações de denúncia de itens e de vendedores.
- Consulta e processa pedidos de adesão e de reactivação de conta,
- Cancela itens
- Suspende contas
- Reactiva contas
- Muda a sua passworcL
- Envia mensagens a utilizadores.
- Consulta os dados de um utilizador dado o seu username.
- Consulta dados de um item dada a sua identificação.
- Modifica ou acrescenta categorias.
- Tem acesso à newsletter (visualização).

**Visitante**

- Tem acesso à newsletter (visualização).
- Tem acesso aos três últimos itens vendidos (descrição, vendido por quanto)
- Pode pedir adesão (fornecendo os dados que descrevem o utilizador)
- Pode pedir reactivação de conta (deve fornecer o username e passward da conta)

3 **Entidades de dados principais**
Não se vai aqui fornecer uma lista de tabelas, A dedução concreda das entidades e tabelas faz parte do trabalho. No entanto é de salientar que existem:
- Utilizadores, descritos por nome, morada, username (único), passward, saldo
 - Pode assumir diversas formas e relações: estás activo, foi suspenso (razão, a pedido?), quais os itens que vendeu, que comprou, que licitou, etc. Está necessariamente associado a outras entidades (itens).
- Itens, descritos por identificação (única), descrição, preço inicial, preço compre-já, prazo, categoria a que pertencem
 - Pode assumir diversas formas e relações: o item ainda está à venda, já foi comprado (quem, quanto)
 - Está associado a utilizadores: quem colocou à venda, quem comprou.

Deve ser usado o bom senso na identificação de outros atributos e entidades auxiliares. O tema do trabalho é relativamente objectivo e é de esperar um esquema de dados adequado.

4 **Arquitectura**

A arquitectura do sistema é obrigatoriamente cliente-servidor:
- Servidor: gere todos os dados do sistema, implementa e valida as regras dos leilões, gere os utilizadores e centraliza tudo. É implementado com JEE no servidor aplicacional (glasfsish).
- Cliente remoto consola: Acede à funcionalidade do sistema segundo o paradigma da consola. São aceites algumas simplificações na funcionalidade decorrentes das restrições típicas do paradigma consola.
- Cliente web-browser. Permite o acesso ao sistema segundo o paradigma de "página web"
