from thrift import Thrift
from thrift.protocol import TCompactProtocol
from thrift.transport import TSocket
from thrift.transport import TTransport
from py.thrift import PersonService, ttypes

try:
    tSocket = TSocket.TSocket('localhost', 9090)
    tSocket.setTimeout(600)

    transport = TTransport.TFramedTransport(tSocket)
    protocol = TCompactProtocol.TCompactProtocol(transport)
    client = PersonService.Client(protocol)

    transport.open()

    person = client.getPersonByUsername("python")

    print(person.name)
    print(person.age)
    print(person.married)

    print("-------")

    newPerson = ttypes.Person()

    newPerson.name = "lanxing"
    newPerson.age = 24
    newPerson.married = True

    client.savePerson(newPerson)

except Thrift.TException as tx:
    print(tx.message)
