# Sistema de Cadastro via CLI 📋

Este projeto foi desenvolvido como parte do desafio proposto no guia **"Java do Básico ao Jr."** do Lucas Carrilho (@devmagro). O objetivo é implementar um sistema de cadastro funcional diretamente no terminal, aplicando conceitos fundamentais de Java, como:

- **Orientação a Objetos (OO)**
- **Manipulação de Arquivos (Java IO)**
- **Streams e Lambdas**
- **Exceções**
- **Boas Práticas de Código**

---

## 📌 Funcionalidades

1. **Cadastro de Usuários:**  
   - Perguntas dinâmicas lidas de um arquivo `formulario.txt`.
   - Validações como: nome com no mínimo 10 caracteres, e-mail com `@`, idade maior que 18 anos e altura no formato correto.
   - Dados são salvos em arquivos `.txt` individuais, nomeados automaticamente.

2. **Listagem de Usuários:**  
   - Mostra os nomes de todos os usuários cadastrados.

3. **Adição e Remoção de Perguntas:**  
   - Possibilidade de adicionar novas perguntas ao formulário.
   - Remoção de perguntas, exceto as quatro iniciais obrigatórias.

4. **Busca Avançada:**  
   - Pesquisa de usuários por nome, e-mail ou idade utilizando Streams.

5. **Validações e Exceções:**  
   - Tratamento de erros, como e-mails duplicados ou perguntas inválidas, com mensagens claras para o usuário.

---

## ⚙️ Como Funciona

1. **Arquivo `formulario.txt`:**  
   Este arquivo contém as perguntas do cadastro e pode ser editado dinamicamente para adicionar ou remover questões (com algumas restrições).  

2. **Menu Principal:**  
   O programa apresenta um menu no terminal com as opções:
   - Cadastrar usuário.
   - Listar todos os usuários.
   - Adicionar novas perguntas ao formulário.
   - Remover perguntas do formulário.
   - Buscar usuários por nome, e-mail ou idade.
   - Sair do programa.

3. **Estrutura de Dados:**  
   - Os dados dos usuários são organizados em objetos, e as operações de leitura/escrita utilizam classes e métodos para manter o código modular e limpo.
