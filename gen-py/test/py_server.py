from thrift import Thrift
from thrift.protocol import TCompactProtocol
from thrift.server import TServer
from thrift.transport import TSocket, TTransport
from py.thrift import PersonService, ttypes
from test.PersonServiceImpl import PersonServiceImpl

try:
    handler = PersonServiceImpl()
    processor = PersonService.Processor(handler)
    transport = TSocket.TServerSocket('127.0.0.1', 9090)
    tfactory = TTransport.TFramedTransportFactory()
    pfactory = TCompactProtocol.TCompactProtocolFactory()
    server = TServer.TSimpleServer(processor, transport, tfactory, pfactory)
    server.serve()
except Thrift.TException as tx:
    print(tx.message)