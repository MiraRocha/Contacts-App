# ContactsApp - Estudo de Arquitetura Android (MVVM + Room + Hilt)

Este projeto é uma aplicação de lista de contactos simples, desenvolvida para fins de estudo das bibliotecas modernas de desenvolvimento Android. O foco principal é a implementação de uma arquitetura limpa e escalável.

## 🚀 Tecnologias e Bibliotecas Utilizadas

- **Kotlin**: Linguagem de programação principal.
- **Jetpack Room**: Abstração sobre o SQLite para persistência de dados local.
- **Dagger Hilt**: Biblioteca para Injeção de Dependências (DI), simplificando a gestão de instâncias de classes.
- **MVVM (Model-View-ViewModel)**: Padrão de arquitetura para separar a lógica de negócio da interface do utilizador.
- **LiveData**: Observável que respeita o ciclo de vida dos componentes (Activity/Fragment).
- **View Binding & Data Binding**: Para interagir de forma segura com os elementos de layout XML.
- **Coroutines**: Para operações assíncronas (como acesso à base de dados) sem bloquear a UI thread.

## 📂 Estrutura do Projeto (Pacotes)

### 1. `room` (Acesso a Dados)
Contém as classes relacionadas com a persistência:
- **`Contact`**: Data class anotada com `@Entity`, representa a tabela "contact" na base de dados.
- **`ContactDao`**: Interface que define os métodos de acesso aos dados (Insert, Query, Delete).
- **`ContactDatabase`**: Classe abstrata que estende `RoomDatabase`. Implementa o padrão Singleton para garantir uma única instância da base de dados.

### 2. `repo` (Repositório)
- **`ContactRepository`**: Atua como uma camada de abstração entre o ViewModel e a fonte de dados (Room). Centraliza a lógica de dados da aplicação.

### 3. `viewmodel`
- **`ContactViewModel`**: Gere os dados da UI. Comunica com o Repositório e expõe a lista de contactos através de `LiveData`. Utiliza o `viewModelScope` para lançar coroutines.

### 4. `di` (Dependency Injection)
- **`MyApp`**: Classe Application anotada com `@HiltAndroidApp`, ponto de entrada para a geração de código do Hilt.
- **`AppModule`**: Módulo Hilt que define como fornecer as instâncias do `ContactDatabase` e do `ContactDao`.

### 5. `util` (Auxiliares)
- **`ContactAdapter`**: Adaptador para o `RecyclerView`. Utiliza `ListAdapter` e `DiffUtil` para uma atualização eficiente e animada da lista.

### 6. UI (`MainActivity`)
- Anotada com `@AndroidEntryPoint` para permitir a injeção do ViewModel.
- Observa o `LiveData` do ViewModel para atualizar a lista automaticamente sempre que houver mudanças.

## 🛠️ Como o Fluxo de Dados Funciona

1. O utilizador clica em "Add" na **MainActivity**.
2. A Activity chama o método `insert` do **ViewModel**.
3. O ViewModel inicia uma **Coroutine** e chama o **Repository**.
4. O Repository executa o comando via **DAO** no **Room**.
5. Como o Room está a retornar um `LiveData`, a mudança na base de dados notifica automaticamente o ViewModel, que por sua vez notifica a Activity para atualizar o **Adapter**.

## 📝 Notas de Implementação

- O uso de `suspend functions` no DAO garante que as operações de escrita/eliminação não congelem a interface.
- O Hilt elimina a necessidade de criar manualmente fábricas de ViewModel (`ViewModelFactory`), tornando o código da `MainActivity` muito mais limpo.


<img width="481" height="933" alt="Captura de ecrã 2026-03-30 144043" src="https://github.com/user-attachments/assets/4d45c7a9-7a7d-475b-84c7-8c6528f417ba" />
