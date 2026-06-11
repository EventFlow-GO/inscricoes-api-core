EventFlowGO - Core API

Este repositório guarda a lógica central do EventFlowGO, cuidando de tudo que envolve as inscrições e o processamento dos nossos eventos.

Quem faz o projeto acontecer:
Ricardo Neres, Thaysa Estanislau, Felipe Calaça, Krystian Souto e Alefe Miguel.

Links importantes:
O código do nosso front-end pode ser encontrado aqui: https://github.com/EventFlow-GO/HUB.git
E o sistema está rodando ao vivo neste endereço: hub-jade-five.vercel.app/

Como o sistema funciona:

Sobre as mensagens e a fila (AWS SQS)
A gente usa o SQS da AWS para que o sistema não fique sobrecarregado. Funciona assim: quando alguém faz uma inscrição, ela entra em uma fila para ser processada com calma. Se algo der errado no meio do caminho, o sistema tenta processar de novo automaticamente antes de desistir, o que deixa tudo muito mais seguro contra falhas.

Onde os dados ficam (Supabase)
Para guardar todas as informações, utilizamos o Supabase. É uma estrutura simples e direta para manter tudo organizado.

O que temos na nossa tabela de inscrições:
- ID: identificador único de cada inscrição.
- Nome: nome de quem está se inscrevendo.
- Email: e-mail de contato da pessoa.
- Senha: senha de acesso escolhida.
- Status: avisa se a inscrição ainda está pendente ou se já foi concluída.

Disponibilidade e compilação (AWS Elastic Beanstalk)
Nosso sistema foi feito para nunca parar. Ele roda 24 horas por dia no Elastic Beanstalk da AWS, que cuida de manter o servidor sempre de pé e pronto para receber novos acessos, sem precisar de intervenção manual o tempo todo.

Como rodar o projeto:
Para testar localmente, você só precisa clonar este repositório, colocar suas credenciais da AWS e do Supabase no arquivo de configurações e iniciar o projeto usando o Maven.
