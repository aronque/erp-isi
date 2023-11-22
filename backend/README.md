# Para poder rodar o backend, siga os seguintes passos:

## Maven: 
Acessar https://maven.apache.org/download.cgi e baixar o segundo item ("Binary zip archive").

Após o download, extrair a pasta "apache-maven-3.9.5" para o diretório de escolha (recomendo C:\).


Depois de extraído para o local correto, configurar o maven nas variáveis de ambiente (este passo ocorrerá junto com a definição do JAVA nas variáveis de ambiente).

## Java: 
Acessar https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html e baixar o primeiro item (.msi para Windows) dado seu sistema operacional.


Após baixar, utilizar o instalador para prosseguir com a instalação. Terminada a instalação, verificar onde foi criada a pasta "Amazon Corretto" (normalmente em: **C:\Program Files\Amazon Corretto**).


Acessar a pasta interna da Amazon Corretto e copiar o caminho da pasta jdk para configurar variável de ambiente (Exemplo: **C:\Program Files\Amazon Corretto\jdk17.0.9_8**).

## Configurar vars de ambiente: 

Pesquisar no windows "Editar as variáveis de ambiente do sistema" e abrir o menu encontrado. 

Na parte inferior do menu, clicar em "Variáveis de Ambiente". Na parte inferior, Variáveis do Sistema, clicar em "Novo" e preencher os campos com os seguintes dados:  


**Nome da variável: JAVA_HOME**

**Valor da variável: C:\Program Files\Amazon Corretto\jdk17.0.9_8**


Selecionar "OK".


Ainda na parte inferior, Variáveis do Sistema, procurar a variável já existente "Path". Selecionar a variável e clicar em "Editar".Clique em "Novo" e copie e cole o caminho completo da pasta .bin dentro da pasta do maven (Exemplo: **C:\apache-maven-3.9.5\bin**).

Após isso feito, acessar a pasta backend com algum CLI (cmd ou git bash) e rodar o comando **mvn spring-boot:run**

---
### Possíveis problemas:
Caso mesmo seguindo os passos não seja possível rodar o backend, definir também, da mesma forma que no passo anterior, o caminho do maven mas agora na variável Path das **variáveis de usuário**, parte superior do menu de variáveis de ambiente, **logo acima das variáveis do sistema**.