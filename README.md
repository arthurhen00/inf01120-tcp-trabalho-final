# inf01120-tcp-trabalho-final  

## Introdução  
Este trabalho é feito em grupo, a princípio o mesmo grupo das Fases 1 e 2.
Comuniquem ao professor se houver mudanças e a partir de qual fase.  

## Descrição:  
O objetivo do trabalho continua o mesmo: a definição, a implementação,
teste e depuração de um GERADOR DE MÚSICA A PARTIR DE TEXTO, tal como
na Fase 1. MAS HÁ MUDANÇAS IMPORTANTES NA DEFINICAO:  

### MUDANÇA 1):  
A entrada do texto pode ser via teclado ou via leitura em um arquivo tipo TXT ou ambos ;-) .
### MUDANÇA 2):  
Além da saída sonora, deve ser possível também salvar o arquivo MIDI gerado. O grupo
decide (i) se o nome seguirá um formato padrão ou será escolhido pelo usuário; e (ii) se o arquivo será gravado
num diretório padrão ou a critério do usuário.  
### MUDANÇA 3):  
Os parâmetros definidos através de um mapeamento de texto para informações musicais FORAM ALTERADOS.
O NOVO mapeamento proposto é o seguinte:

| Texto         | Informação Musical ou Ação |
| ------------- | ------------- |
| Letra A maiúscula | Nota Lá |
| Letra B maiúscula | Nota Si |
| Letra C maiúscula | Nota Dó |
| Letra D maiúscula | Nota Ré |
| Letra E maiúscula | Nota Mi |
| Letra F maiúscula | Nota Fá |
| Letra G maiúscula | Nota Sol |
| Letras a,b,c,d,e,f,g minúsculas | Se caractere anterior era NOTA (A a G), repete nota; Caso contrário, Silêncio ou pausa |
| Caractere Espaço | Aumenta volume para o DOBRO do volume; Se não puder aumentar, volta ao volume default (de início) |
| Caractere ! (ponto de exclamação) | Trocar instrumento para o instrumento General MIDI #114 (Agogo) |
| Qualquer outra letra vogal (O ou o, I ou i , U ou u) | Trocar instrumento para o instrumento General MIDI #7 (Harpsichord) |
| Qualquer outra letra consoante (todas consoantes exceto as que são notas) | Se caractere anterior era NOTA (A a G), repete nota; Caso contrário, Silêncio ou pausa |
| Dígito par ou impar | Trocar instrumento para o instrumento General MIDI cujo numero é igual ao valor do instrumento ATUAL + valor do dígito |
| Caractere ? (ponto de interrogação) e caractere . (ponto) | Aumenta UMA oitava; Se não puder, aumentar, volta à oitava default (de início) |
| Caractere NL (nova linha) | Trocar instrumento para o instrumento General MIDI #15 (Tubular Bells)  |
| Caractere ; (ponto e vírgula) | Trocar instrumento para o instrumento General MIDI #76 (Pan Flute) |
| Caractere , (vírgula) | Trocar instrumento para o instrumento General MIDI #20 (Church Organ) |
| ELSE (nenhum dos caracteres anteriores) | Se caractere anterior era NOTA (A a G), repete nota; Caso contrário, Silêncio ou pausa |
